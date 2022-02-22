package com.mohylov.diet.ui.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.ui.domain.products.ProductsInteractor
import com.mohylov.diet.ui.domain.toProductItem
import com.mohylov.diet.ui.domain.toProductViewItem
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.main.adapters.ProductViewItem
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate

class SearchViewModel(
    private val mealInfo: MealInfo,
    private val productsInteractor: ProductsInteractor,
    private val mealProductsManagementInteractor: MealProductsManagementInteractor
) : BaseViewModel<SearchViewState, SearchViewActions>() {

    init {
        initViewState()
    }

    fun onSearchTextChanged(searchQuery: String) {
        productsInteractor.getProductsBySearchFilter(searchQuery)
            .onEach { products ->
                updateState(getViewState().copy(
                    filteredProducts = products.map { it.toProductViewItem() }
                ))
            }.launchIn(viewModelScope)

    }

    fun onProductClicked(productViewItem: ProductViewItem) {
        updateAction(
            SearchViewActions.AmountConfirmationAction(
                productId = productViewItem.id,
                dedaultAmount = 100
            )
        )
    }

    fun onProductAmountSelected(amountInfo: AmountInfo) {
        viewModelScope.launch {
            val productItem = getViewState().filteredProducts.find { it.id == amountInfo.productId }
                ?: return@launch
            mealProductsManagementInteractor.insertMealProduct(
                mealType = mealInfo.mealType,
                productItem = productItem.toProductItem(),
                date = LocalDate.parse(mealInfo.date),
                amount = amountInfo.amount
            )
            navigate(NavigationActions.PopBackStack)
        }
    }

    private fun getViewState() = stateData.value as SearchViewState

    private fun initViewState() {
        updateState(
            SearchViewState(
                isLoading = true,
                filteredProducts = emptyList()
            )
        )
    }

    class Factory @AssistedInject constructor(
        @Assisted("mealInfo") val mealInfo: MealInfo,
        private val productsInteractor: ProductsInteractor,
        private val mealProductsManagementInteractor: MealProductsManagementInteractor
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == SearchViewModel::class.java)
            return SearchViewModel(
                mealInfo = mealInfo,
                productsInteractor = productsInteractor,
                mealProductsManagementInteractor = mealProductsManagementInteractor
            ) as T
        }

    }

    @AssistedFactory
    interface ViewModelAssistFactory {

        fun create(@Assisted("mealInfo") mealInfo: MealInfo): Factory
    }

}

data class SearchViewState(
    val isLoading: Boolean,
    val filteredProducts: List<ProductViewItem>
)

sealed class SearchViewActions {

    class AmountConfirmationAction(val productId: Long, val dedaultAmount: Int) :
        SearchViewActions()
}


