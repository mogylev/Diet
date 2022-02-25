package com.mohylov.diet.presentation.base

import androidx.lifecycle.ViewModel

/**
 * Components viewModel  that hold dagger component and survive
 * configuration changes.
 */
class ComponentHolder<T>(val component: T) : ViewModel()