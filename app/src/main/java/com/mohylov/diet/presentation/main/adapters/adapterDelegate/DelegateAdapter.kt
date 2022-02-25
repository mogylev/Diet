package com.mohylov.diet.presentation.main.adapters.adapterDelegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class DelegateAdapter<T, VH>(val modelClass: Class<out T>) where
T : DelegateAdapterItem, VH : RecyclerView.ViewHolder {

    abstract fun createViewHolder(parent: ViewGroup): VH

    abstract fun bindViewHolder(item: T, holder: VH)

}