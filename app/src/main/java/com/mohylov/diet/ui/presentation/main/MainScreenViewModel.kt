package com.mohylov.diet.ui.presentation.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mohylov.diet.ui.domain.mealProducts.MealProductsInteractor
import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.ui.domain.mealProductsCalculator.MealProductCalculateInteractor
import com.mohylov.diet.ui.domain.mealProductsCalculator.entities.NutrtientResult
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.main.adapters.ProductViewItem
import com.mohylov.diet.ui.presentation.main.adapters.adapterDelegate.DelegateAdapterItem
import com.mohylov.diet.ui.presentation.main.adapters.entities.MealHeaderDelegateItem
import com.mohylov.diet.ui.presentation.main.adapters.entities.MealItem
import com.mohylov.diet.ui.presentation.main.adapters.entities.MealProductDelegateAdapterItem
import com.mohylov.diet.ui.presentation.search.MealInfo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val mealProductsInteractor: MealProductsInteractor,
    private val mealProductsManagementInteractor: MealProductsManagementInteractor,
    private val mealProductsCalculatorInteractor: MealProductCalculateInteractor
) : BaseViewModel<MainScreenViewState, MainScreenViewActions>() {

    init {
        initViewState()
    }

    private val mealItems = MealItem.emptyMeals()

    fun updateData() {
        mealProductsInteractor.getMealProductsByDate(getViewState().date).onEach { mealProducts ->
            val nutrientResult = mealProductsCalculatorInteractor.calculateNutrients(mealProducts)
            val mealsDelegateItems = buildMealList(mealProducts)
            updateState(
                getViewState().copy(
                    mealsItems = mealsDelegateItems,
                    nutrientsResult = nutrientResult
                )
            )
        }.launchIn(viewModelScope)
    }

    fun onMealHeaderClick(mealHeader: MealHeaderDelegateItem) {
        navigate(
            NavigationActions.NavigationAction(
                MainScreenFragmentDirections.actionMainScreenFragmentToSearchFragment(
                    mealInfo = MealInfo(
                        mealType = mealHeader.mealType,
                        date = getViewState().date.toString()
                    )
                )
            )
        )
    }

    fun onMealProductLongClick(product: MealProductDelegateAdapterItem) {
        updateAction(
            MainScreenViewActions.RemoveProductRequestAction(product)
        )
    }

    fun onRemoveProductConfirm(product: MealProductDelegateAdapterItem) {
        viewModelScope.launch {
            mealProductsManagementInteractor.removeMealProduct(product.id)
            updateData()
        }
    }

    private fun buildMealList(mealProducts: List<MealProductItem>): List<DelegateAdapterItem> {
        val mealsDelegateItems = mutableListOf<DelegateAdapterItem>()
        mealItems.forEach { mealItem ->
            mealsDelegateItems.add(mealItem.toMealHeaderDelegateAdapterItem())
            mealsDelegateItems.addAll(mealProducts.filter { it.type == mealItem.mealType }
                .map { it.toMealProductDelegateAdapterItem() })
        }
        return mealsDelegateItems
    }

    private fun initViewState() {
        stateData.value = MainScreenViewState(
            isLoading = true,
            mealsItems = MealItem.emptyMeals().map { it.toMealHeaderDelegateAdapterItem() }
        )
    }

    private fun getViewState() = stateData.value as MainScreenViewState

}

data class MainScreenViewState(
    val isLoading: Boolean,
    val date: LocalDate = LocalDate.now(),
    val mealsItems: List<DelegateAdapterItem>,
    val nutrientsResult: NutrtientResult = NutrtientResult.empty()
)

sealed class MainScreenViewActions {

    data class RemoveProductRequestAction(val product: MealProductDelegateAdapterItem) :
        MainScreenViewActions()
}

fun MealItem.toMealHeaderDelegateAdapterItem(): MealHeaderDelegateItem {
    return MealHeaderDelegateItem(
        mealType = mealType,
        mealNameResId = meanNameResId
    )
}

fun MealProductItem.toMealProductDelegateAdapterItem(): MealProductDelegateAdapterItem {
    return MealProductDelegateAdapterItem(
        id = id,
        name = name,
        protein = protein,
        fats = fats,
        carbohydrates = carbohydrates,
        calories = calories
    )
}
