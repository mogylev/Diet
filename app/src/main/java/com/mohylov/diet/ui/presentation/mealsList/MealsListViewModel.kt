package com.mohylov.diet.ui.presentation.mealsList

import com.mohylov.diet.ui.domain.filter.FiltersInteractor
import com.mohylov.diet.ui.domain.mealProducts.MealProductsInteractor
import com.mohylov.diet.ui.domain.mealProducts.entities.MealProductItem
import com.mohylov.diet.ui.domain.nutrientsCalculator.NutrientsCalculateInteractor
import com.mohylov.diet.ui.domain.nutrientsCalculator.entities.NutrtientResult
import com.mohylov.diet.ui.domain.mealProductsManagement.MealProductsManagementInteractor
import com.mohylov.diet.ui.presentation.base.BaseViewAction
import com.mohylov.diet.ui.presentation.base.BaseViewModel
import com.mohylov.diet.ui.presentation.base.BaseViewState
import com.mohylov.diet.ui.presentation.base.NavigationActions
import com.mohylov.diet.ui.presentation.mealsList.adapters.adapterDelegate.DelegateAdapterItem
import com.mohylov.diet.ui.presentation.mealsList.entities.MealHeaderDelegateItem
import com.mohylov.diet.ui.presentation.mealsList.entities.MealItem
import com.mohylov.diet.ui.presentation.mealsList.entities.MealProductDelegateAdapterItem
import com.mohylov.diet.ui.presentation.mealEdit.entities.MealProductInfo
import com.mohylov.diet.ui.presentation.search.entities.MealInfo
import kotlinx.coroutines.flow.first
import java.time.Instant
import javax.inject.Inject


class MealsListViewModel @Inject constructor(
    private val mealProductsInteractor: MealProductsInteractor,
    private val mealProductsManagementInteractor: MealProductsManagementInteractor,
    private val mealProductsCalculatorInteractor: NutrientsCalculateInteractor,
    private val filtersInteractor: FiltersInteractor
) : BaseViewModel<MainScreenViewState, MainScreenViewActions>() {

    private val mealItems = MealItem.emptyMeals()

    init {
        initViewState()
    }

    fun updateData() {
        async(work = {
            val selectedDate = filtersInteractor.getSelectedDate().first()
            mealProductsInteractor.getMealProductsByDate(selectedDate).let { mealProducts ->
                val nutrientResult =
                    mealProductsCalculatorInteractor.calculateNutrients(mealProducts)
                val mealsDelegateItems = buildMealList(mealProducts)
                updateState(
                    getViewState().copy(
                        mealsItems = mealsDelegateItems,
                        nutrientsResult = nutrientResult
                    )
                )
            }
        })
    }

    fun onMealHeaderClick(mealHeader: MealHeaderDelegateItem) {
        navigate(
            NavigationActions.NavigationAction(
                MealsListFragmentDirections.actionMealsListFragmentToSearchFragment(
                    mealInfo = MealInfo(
                        mealType = mealHeader.mealType,
                        date = getViewState().date.toEpochMilli(),
                        mealNameResId = mealHeader.mealNameResId
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

    fun onMealProductClick(product: MealProductDelegateAdapterItem) {
        navigate(
            NavigationActions.NavigationAction(
                MealsListFragmentDirections.actionMealsListFragmentToMealEditFragment(
                    mealProductInfo = MealProductInfo(
                        id = product.id,
                        productName = product.name,
                    )
                )
            )
        )
    }

    fun onRemoveProductConfirm(product: MealProductDelegateAdapterItem) {
        async(work = {
            mealProductsManagementInteractor.removeMealProduct(product.id)
            updateData()
        })
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
            date = Instant.now(),
            mealsItems = MealItem.emptyMeals().map { it.toMealHeaderDelegateAdapterItem() }
        )
    }

    private fun getViewState() = stateData.value as MainScreenViewState

}

data class MainScreenViewState(
    val isLoading: Boolean,
    val date: Instant,
    val mealsItems: List<DelegateAdapterItem>,
    val nutrientsResult: NutrtientResult = NutrtientResult.empty()
) : BaseViewState

sealed class MainScreenViewActions : BaseViewAction {

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
