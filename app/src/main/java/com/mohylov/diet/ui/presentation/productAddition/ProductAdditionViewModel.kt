package com.mohylov.diet.ui.presentation.productAddition

import androidx.lifecycle.viewModelScope
import com.mohylov.diet.ui.domain.products.ProductsInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.productAddition.entities.ProductInputData
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductAdditionViewModel @Inject constructor(
    private val productsInteractor: ProductsInteractor
) :
    BaseViewModel<ProductAdditionViewState, ProductAdditionViewActions>() {

    fun onBackPressed() {
        navigate(NavigationActions.PopBackStack)
    }

    fun onAddClick(productInputData: ProductInputData) {
        viewModelScope.launch {
            productsInteractor.createProduct(
                productName = productInputData.productName,
                caloriesAmount = productInputData.productCalories.toIntOrNull() ?: 0,
                proteinsAmount = productInputData.productProteins.toFloatOrNull() ?: 0f,
                fatsAmount = productInputData.productFats.toFloatOrNull() ?: 0f,
                carbohydratesAmount = productInputData.productCarbohydrates.toFloatOrNull() ?: 0f
            )
            navigate(NavigationActions.PopBackStack)
        }
    }
}

data class ProductAdditionViewState(val isLoading: Boolean)

sealed class ProductAdditionViewActions {

}