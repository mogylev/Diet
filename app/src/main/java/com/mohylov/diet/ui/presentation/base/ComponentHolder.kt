package com.mohylov.diet.ui.presentation.base

import androidx.lifecycle.ViewModel

/**
 * Components viewModel  that hold dagger component and survive
 * configuration changes.
 */
class ComponentHolder<T>(val component: T) : ViewModel()