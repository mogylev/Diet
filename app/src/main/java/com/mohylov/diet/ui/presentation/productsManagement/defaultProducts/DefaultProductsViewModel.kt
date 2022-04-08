package com.mohylov.diet.ui.presentation.productsManagement.defaultProducts

import com.mohylov.diet.ui.domain.products.defaultProducts.DefaultProductsInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewAction
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import com.mohylov.diet.ui.presentation.base.BaseViewState
import com.mohylov.diet.ui.presentation.mappers.toProductViewItem
import com.mohylov.diet.ui.presentation.mealsList.adapters.ProductViewItem
import javax.inject.Inject

class DefaultProductsViewModel @Inject constructor(
    private val defaultProductsInteractor: DefaultProductsInteractor
) : BaseViewModel<DefaultProductsViewState, DefaultProductsViewAction>() {

    private var searchFilter = ""

    init {
        initState()
    }

    fun onFilterChanged(it: String) {
        searchFilter = it
        refreshData()
    }

    fun refreshData() {
        async(work = {
            updateState(
                getState().copy(
                    products = defaultProductsInteractor.searchDefaultProducts(searchFilter)
                        .map { it.toProductViewItem() }
                )
            )
        })
    }

    private fun getState(): DefaultProductsViewState {
        return stateData.value as DefaultProductsViewState
    }

    private fun initState() {
        stateData.value = DefaultProductsViewState()
    }
}

data class DefaultProductsViewState(
    val isLoading: Boolean = false,
    val products: List<ProductViewItem> = emptyList()
) : BaseViewState

sealed class DefaultProductsViewAction : BaseViewAction {

}

