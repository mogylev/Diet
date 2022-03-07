package com.mohylov.diet.ui.presentation.mealEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mohylov.diet.ui.domain.completeMealProduct.CompleteMealProductInteractor
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class MealEditViewModel(
    private val mealProductInfo: MealProductInfo,
    private val mealProductsManagementInteractor: MealProductsManagementInteractor,
    private val completeMealProductInteractor: CompleteMealProductInteractor
) : BaseViewModel<MealEditViewState, MealEditViewActions>() {

    init {
        initialState()
        updateData()
    }

    private fun updateData() {
        viewModelScope.launch {
            val completeMealProductItem =
                completeMealProductInteractor.getCompleteMealProduct(mealProductId = mealProductInfo.id)
            updateState(
                getViewState().copy(
                    productValue = completeMealProductItem.amount,
                    productNutrientsInfo = ProductNutrientsInfo(
                        proteins = completeMealProductItem.productItem.protein,
                        fats = completeMealProductItem.productItem.fats,
                        carbohydrates = completeMealProductItem.productItem.carbohydrates,
                        calories = completeMealProductItem.productItem.calories
                    )
                )
            )
        }
    }

    class ViewModelFactory @AssistedInject constructor(
        @Assisted("mealProductInfo") private val mealProductInfo: MealProductInfo,
        private val mealProductsManagementInteractor: MealProductsManagementInteractor,
        private val completeMealProductInteractor: CompleteMealProductInteractor
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == MealEditViewModel::class.java)
            return MealEditViewModel(
                mealProductInfo,
                mealProductsManagementInteractor,
                completeMealProductInteractor
            ) as T
        }

    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("mealProductInfo") mealProductInfo: MealProductInfo): ViewModelFactory
    }

    private fun initialState() {
        stateData.value = MealEditViewState(
            isLoading = false,
            productName = mealProductInfo.productName,
            productValue = 0,
            productNutrientsInfo = ProductNutrientsInfo.empty()
        )
    }

    private fun getViewState(): MealEditViewState {
        return stateData.value as MealEditViewState
    }

}

data class MealEditViewState(
    val isLoading: Boolean,
    val productName: String,
    val productValue: Int,
    val productNutrientsInfo: ProductNutrientsInfo)

sealed class MealEditViewActions {

}
