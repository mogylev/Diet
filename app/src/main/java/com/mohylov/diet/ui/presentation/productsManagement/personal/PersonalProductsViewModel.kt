package com.mohylov.diet.ui.presentation.productsManagement.personal

import android.util.Log
import com.mohylov.diet.ui.domain.products.personal.PersonalProductsInteractor
import com.mohylov.diet.ui.domain.products.remove.ProductRemovingInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewAction
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import com.mohylov.diet.ui.presentation.base.BaseViewState
import com.mohylov.diet.ui.presentation.mappers.toProductViewItem
import com.mohylov.diet.ui.presentation.mealsList.adapters.PopUpMenuItem
import com.mohylov.diet.ui.presentation.mealsList.adapters.ProductViewItem
import javax.inject.Inject

class PersonalProductsViewModel @Inject constructor(
    private val personalProductsInteractor: PersonalProductsInteractor,
    private val productRemovingInteractor: ProductRemovingInteractor
) :
    BaseViewModel<PersonalProductsViewState, PersonalProductsViewActions>() {

    init {
        initState()
        initPersonalProducts()
    }

    private fun getState(): PersonalProductsViewState {
        return stateData.value as PersonalProductsViewState
    }

    private fun initState() {
        stateData.value = PersonalProductsViewState()
    }

    fun onPopupMenuSelected(selection: Map<PopUpMenuItem, ProductViewItem>) {
        selection.forEach { key, value ->
            when (key) {
                PopUpMenuItem.Delete -> {
                    removeProduct(value)
                }
            }
        }
    }

    private fun removeProduct(product: ProductViewItem) {
        async(work = {
            productRemovingInteractor.removeProduct(
                product.id
            )
            initPersonalProducts()
        })
    }

    private fun initPersonalProducts() {
        async(work = {
            updateState(
                getState().copy(
                    productList = personalProductsInteractor.getPersonalProducts().take(10)
                        .map { it.toProductViewItem() }
                )
            )
        })
    }

}

data class PersonalProductsViewState(
    val isLoading: Boolean = false,
    val productList: List<ProductViewItem> = emptyList()
) : BaseViewState

sealed class PersonalProductsViewActions : BaseViewAction