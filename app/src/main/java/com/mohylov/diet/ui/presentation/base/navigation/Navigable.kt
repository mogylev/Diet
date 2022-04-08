package com.mohylov.diet.ui.presentation.base.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mohylov.diet.R
import com.mohylov.diet.ui.presentation.base.NavigationActions

interface Navigable

fun Fragment.handleNavigation(navActions: NavigationActions) {
    when (navActions) {
        is NavigationActions.NavigationAction -> {
            findNavController().navigate(navActions.direction)
        }
        is NavigationActions.MainNavigation -> {
            Navigation.findNavController(requireActivity(), R.id.main_nav_container).apply {
                navigate(navActions.direction, navActions.navOptions)
            }
        }
        is NavigationActions.PopBackStack -> {
            findNavController().popBackStack()
        }
    }
}