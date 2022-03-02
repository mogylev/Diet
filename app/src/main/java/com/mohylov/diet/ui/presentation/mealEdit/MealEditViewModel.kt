package com.mohylov.diet.ui.presentation.mealEdit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohylov.diet.ui.domain.mealProducts.MealProductsInteractor
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

class MealEditViewModel(
    private val mealProductId: Long,
    private val productsInteractor: MealProductsInteractor,
    private val mealProductsInteractor: MealProductsInteractor,
    private val mealProductsManagementInteractor: MealProductsManagementInteractor
) : BaseViewModel<MealEditViewState, MealEditViewActions>() {

    init {
        initViewState()
        Log.e("tag!!!", ":  $mealProductId", )
    }


    class ViewModelFactory @AssistedInject constructor(
        @Assisted private val mealProductId: Long,
        private val productsInteractor: MealProductsInteractor,
        private val mealProductsInteractor: MealProductsInteractor,
        private val mealProductsManagementInteractor: MealProductsManagementInteractor
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            check(modelClass.isAssignableFrom(MealEditViewModel::class.java))
            return MealEditViewModel(
                mealProductId,
                productsInteractor,
                mealProductsInteractor,
                mealProductsManagementInteractor
            ) as T
        }

    }

    @AssistedFactory
    interface Factory {
        fun create(mealProductId: Long): ViewModelFactory
    }

    private fun initViewState() {
        stateData.value = MealEditViewState(false)
    }

    private fun getViewState(): MealEditViewState {
        return stateData.value as MealEditViewState
    }

}

data class MealEditViewState(
    val isLoading: Boolean
)

sealed class MealEditViewActions {

}