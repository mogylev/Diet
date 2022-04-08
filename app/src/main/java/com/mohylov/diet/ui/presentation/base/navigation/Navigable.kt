package com.mohylov.diet.ui.presentation.base.navigation

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mohylov.diet.R
import com.mohylov.diet.ui.presentation.base.NavigationActions
import java.lang.Exception

interface Navigable

fun Fragment.handleNavigation(navActions: NavigationActions) {
    when (navActions) {
        is NavigationActions.NavigationAction -> {
            findNavController().navigate(navActions.direction)
        }
        is NavigationActions.DietNavigation -> {
            try {
                Navigation.findNavController(requireActivity(), R.id.main_nav_container).apply {
                    Log.e("tag!!!", "handleNavigation:  ${this.graph} ", )
                }
                    .navigate(navActions.direction, navActions.navOptions)

            } catch (e: Exception) {
                Log.e("tag!!!", "handleNavigation:  $e")
            }
        }
        is NavigationActions.PopBackStack -> {
            findNavController().popBackStack()
        }
    }
}