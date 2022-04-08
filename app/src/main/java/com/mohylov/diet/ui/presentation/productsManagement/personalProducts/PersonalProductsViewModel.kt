package com.mohylov.diet.ui.presentation.productsManagement.personalProducts

import com.mohylov.diet.ui.domain.products.personalProducts.PersonalProductsInteractor
import com.mohylov.diet.ui.domain.products.remove.ProductRemovingInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewAction
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import com.mohylov.diet.ui.presentation.base.BaseViewState
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.main.MainFragmentDirections
import com.mohylov.diet.ui.presentation.main.MainNavFragment
import com.mohylov.diet.ui.presentation.mappers.toProductViewItem
import com.mohylov.diet.ui.presentation.mealsList.adapters.ProductViewItem
import com.mohylov.diet.ui.presentation.productsManagement.personalProducts.adapters.PopUpMenuItem
import javax.inject.Inject

class PersonalProductsViewModel @Inject constructor(
    private val personalProductsInteractor: PersonalProductsInteractor,
    private val productRemovingInteractor: ProductRemovingInteractor
) :
    BaseViewModel<PersonalProductsViewState, PersonalProductsViewActions>() {

    private var searchFilter = ""

    init {
        initState()
    }

    fun onPopupMenuSelected(selection: Map<PopUpMenuItem, ProductViewItem>) {
        selection.forEach { (key, value) ->
            when (key) {
                PopUpMenuItem.Delete -> {
                    removeProduct(value)
                }
            }
        }
    }

    fun onFilterChanged(it: String) {
        searchFilter = it
        refreshData()
    }

    fun refreshData() {
        async(work = {
            updateState(
                getState().copy(
                    productList = personalProductsInteractor.searchPersonalProducts(searchFilter)
                        .map { it.toProductViewItem() }
                )
            )
        })
    }

    private fun removeProduct(product: ProductViewItem) {
        async(work = {
            productRemovingInteractor.removeProduct(
                product.id
            )
            refreshData()
        })
    }

    private fun getState(): PersonalProductsViewState {
        return stateData.value as PersonalProductsViewState
    }

    private fun initState() {
        stateData.value = PersonalProductsViewState()
    }

    fun onProductAdditionClick() {
        navigate(
            NavigationActions.MainNavigation(
                MainFragmentDirections.actionMainFragmentToProductAdditionFragment()
            )
        )
    }

}

data class PersonalProductsViewState(
    val isLoading: Boolean = false,
    val productList: List<ProductViewItem> = emptyList()
) : BaseViewState

sealed class PersonalProductsViewActions : BaseViewAction