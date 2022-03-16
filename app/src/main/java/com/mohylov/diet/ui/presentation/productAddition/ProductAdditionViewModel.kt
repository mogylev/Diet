package com.mohylov.diet.ui.presentation.productAddition

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.mohylov.diet.ui.domain.products.ProductsInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewAction
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import com.mohylov.diet.ui.presentation.base.BaseViewState
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.productAddition.entities.ProductInputData
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductAdditionViewModel @Inject constructor(
    private val productsInteractor: ProductsInteractor
) :
    BaseViewModel<ProductAdditionViewState, ProductAdditionViewActions>() {

    init {
        initState()
    }

    fun onBackPressed() {
        navigate(NavigationActions.PopBackStack)
    }

    fun onAddClick(productInputData: ProductInputData) {
        async(work = {
            if (validateInputData(productInputData)) {
                productsInteractor.createProduct(
                    productName = productInputData.productName,
                    caloriesAmount = productInputData.productCalories.toIntOrNull() ?: 0,
                    proteinsAmount = productInputData.productProteins.toFloatOrNull() ?: 0f,
                    fatsAmount = productInputData.productFats.toFloatOrNull() ?: 0f,
                    carbohydratesAmount = productInputData.productCarbohydrates.toFloatOrNull()
                        ?: 0f
                )
                navigate(NavigationActions.PopBackStack)
            }
        })
    }

    private fun validateInputData(productInputData: ProductInputData):Boolean {
        var allDataIsValid = true
        val productNameState = with(productInputData.productName) {
            if (isEmpty()) {
                allDataIsValid = false
                ProductValidationState.EmptyField
            } else {
                ProductValidationState.Valid
            }
        }

        val proteinsState = with(productInputData.productProteins) {
            if (isEmpty()) {
                allDataIsValid = false
                ProductValidationState.EmptyField
            } else {
                ProductValidationState.Valid
            }
        }

        val fatsState = with(productInputData.productFats) {
            if (isEmpty()) {
                allDataIsValid = false
                ProductValidationState.EmptyField
            } else {
                ProductValidationState.Valid
            }
        }

        val caloriesState = with(productInputData.productCalories) {
            if (isEmpty()) {
                allDataIsValid = false
                ProductValidationState.EmptyField
            } else {
                ProductValidationState.Valid
            }
        }

        val carbohydratesState = with(productInputData.productCarbohydrates) {
            if (isEmpty()) {
                allDataIsValid = false
                ProductValidationState.EmptyField
            } else {
                ProductValidationState.Valid
            }
        }

        updateState(getState().copy(
            productNameProductValidationState = productNameState,
            caloriesProductValidationState = caloriesState,
            proteinsProductValidationState = proteinsState,
            fatsProductValidationState = fatsState,
            carbohydratesProductValidationState = carbohydratesState
        ))

        return allDataIsValid
    }

    private fun initState() {
        stateData.value = ProductAdditionViewState(isLoading = false)
    }

    private fun getState(): ProductAdditionViewState {
        return stateData.value as ProductAdditionViewState
    }

}

data class ProductAdditionViewState(
    val isLoading: Boolean,
    val productNameProductValidationState: ProductValidationState = ProductValidationState.Default,
    val caloriesProductValidationState: ProductValidationState = ProductValidationState.Default,
    val proteinsProductValidationState: ProductValidationState = ProductValidationState.Default,
    val fatsProductValidationState: ProductValidationState = ProductValidationState.Default,
    val carbohydratesProductValidationState: ProductValidationState = ProductValidationState.Default
) : BaseViewState

sealed class ProductValidationState {
    object Default : ProductValidationState()
    object EmptyField : ProductValidationState()
    data class Invalid(@StringRes val errorResId: Int) : ProductValidationState()
    object Valid : ProductValidationState()
}


sealed class ProductAdditionViewActions : BaseViewAction {

}
