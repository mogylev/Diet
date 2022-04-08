package com.mohylov.diet.ui.presentation.mealsList.adapters.adapterDelegate

interface DelegateAdapterItem : Equatable {

    fun id(): Any

    fun content(): Equatable

}