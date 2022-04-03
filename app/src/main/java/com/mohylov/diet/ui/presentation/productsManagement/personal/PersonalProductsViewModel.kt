package com.mohylov.diet.ui.presentation.productsManagement.personal

import com.mohylov.diet.ui.domain.products.entities.ProductItem
import com.mohylov.diet.ui.domain.products.personal.PersonalProductsInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewAction
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import com.mohylov.diet.ui.presentation.base.BaseViewState
import javax.inject.Inject

class PersonalProductsViewModel @Inject constructor(private val personalProductsInteractor: PersonalProductsInteractor) :
    BaseViewModel<PersonalProductsViewState, PersonalProductsViewActions>() {

    init {
        initState()
        initPersonalProducts()
    }

    private fun initPersonalProducts() {
        async(work = {
            updateState(
                getState().copy(
                    productList = personalProductsInteractor.getPersonalProducts().take(10)
                )
            )
        })
    }

    private fun getState(): PersonalProductsViewState {
        return stateData.value as PersonalProductsViewState
    }

    private fun initState() {
        stateData.value = PersonalProductsViewState()
    }
}

data class PersonalProductsViewState(
    val isLoading: Boolean = false,
    val productList: List<ProductItem> = emptyList()
) : BaseViewState

sealed class PersonalProductsViewActions : BaseViewAction