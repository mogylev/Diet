package com.mohylov.diet.ui.presentation.mealEdit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mohylov.diet.ui.domain.completeMealProduct.CompleteMealProductInteractor
import com.mohylov.diet.ui.domain.completeMealProduct.entities.CompleteMealProductItem
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewAction
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import com.mohylov.diet.ui.presentation.base.BaseViewState
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.mealEdit.entities.MealProductInfo
import com.mohylov.diet.ui.presentation.mealEdit.entities.CompleteMealProductModel
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


    fun onSaveClicked(amount: Int) {
        viewModelScope.launch {
            mealProductsManagementInteractor.updateMealProduct(
                mealProductId = mealProductInfo.id,
                amount = amount
            )
            navigate(NavigationActions.PopBackStack)
        }
    }

    private fun updateData() {
        viewModelScope.launch {
            val completeMealProductItem =
                completeMealProductInteractor.getCompleteMealProduct(mealProductId = mealProductInfo.id)
            updateState(
                getViewState().copy(
                    completeMealProductModel = completeMealProductItem.toCompleteMealProductModel()
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
    val completeMealProductModel: CompleteMealProductModel? = null
) : BaseViewState

sealed class MealEditViewActions : BaseViewAction {

}

fun CompleteMealProductItem.toCompleteMealProductModel(): CompleteMealProductModel {
    return CompleteMealProductModel(
        amount = amount,
        mealType = type,
        productItem = productItem,
        date = date
    )
}
