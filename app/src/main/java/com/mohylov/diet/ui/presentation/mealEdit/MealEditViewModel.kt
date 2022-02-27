package com.mohylov.diet.ui.presentation.mealEdit

import com.mohylov.diet.ui.presentation.base.BaseViewModel

class MealEditViewModel : BaseViewModel<MealEditViewState, MealEditViewActions>() {

}

data class MealEditViewState(
    val isLoading: Boolean
)

sealed class MealEditViewActions {

}