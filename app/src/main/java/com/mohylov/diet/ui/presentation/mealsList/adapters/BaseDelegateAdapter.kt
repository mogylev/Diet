package com.mohylov.diet.ui.presentation.mealsList.adapters

import android.annotation.SuppressLint
import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohylov.diet.ui.presentation.mealsList.adapters.adapterDelegate.DelegateAdapter
import com.mohylov.diet.ui.presentation.mealsList.adapters.adapterDelegate.DelegateAdapterItem
import java.lang.IllegalArgumentException

val callback = object : DiffUtil.ItemCallback<DelegateAdapterItem>() {

    override fun areItemsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean {
        return oldItem.id() == newItem.id() && oldItem::class == newItem::class
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: DelegateAdapterItem,
        newItem: DelegateAdapterItem
    ): Boolean {
        return newItem.content() == oldItem.content()
    }

}

class BaseDelegateAdapter(
    private val delegates: SparseArray<DelegateAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>>
) : ListAdapter<DelegateAdapterItem, RecyclerView.ViewHolder>(callback) {

    override fun getItemViewType(position: Int): Int {
        for (i in 0 until delegates.size()) {
            if (delegates[i].modelClass == getItem(position).javaClass) {
                return delegates.keyAt(i)
            }
        }
        throw IllegalArgumentException("Adapter has now proper viewType")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegates[viewType].createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegates[getItemViewType(position)].bindViewHolder(getItem(position), holder)
    }

    override fun getItemCount(): Int = currentList.size


}
