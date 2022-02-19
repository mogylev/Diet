package com.mohylov.diet.ui.presentation.base

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

sealed class NavigationActions {
    data class NavigationAction(val direction: NavDirections, val navOptions: NavOptions? = null) : NavigationActions()
    object PopBackStack : NavigationActions()
}
