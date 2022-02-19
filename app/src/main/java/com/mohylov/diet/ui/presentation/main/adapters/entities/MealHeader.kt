package com.mohylov.diet.ui.presentation.main.adapters.entities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohylov.diet.databinding.MealHeaderItemBinding
import com.mohylov.diet.ui.domain.mealProducts.entities.MealType
import com.mohylov.diet.ui.presentation.main.adapters.adapterDelegate.DelegateAdapter
import com.mohylov.diet.ui.presentation.main.adapters.adapterDelegate.DelegateAdapterItem
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class MealHeaderDelegateItem(
    val mealType: MealType,
    val mealNameResId: Int
) : DelegateAdapterItem {

    override fun id(): Any {
        return mealType
    }

    override fun content(): Any {
        return mealType
    }

}

class MealHeaderAdapterDelegate :
    DelegateAdapter<MealHeaderDelegateItem, MealHeaderViewHolder>(MealHeaderDelegateItem::class.java) {

    private val _clickFlow = MutableSharedFlow<MealHeaderDelegateItem>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val clickFlow: Flow<MealHeaderDelegateItem> = _clickFlow

    override fun createViewHolder(parent: ViewGroup): MealHeaderViewHolder {
        return MealHeaderViewHolder(
            MealHeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            binding.root.setOnClickListener {
                _clickFlow.tryEmit(mealHeaderDelegateItem ?: return@setOnClickListener)
            }
        }
    }

    override fun bindViewHolder(item: MealHeaderDelegateItem, holder: MealHeaderViewHolder) {
        holder.bind(item)
    }
}

class MealHeaderViewHolder(val binding: MealHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var mealHeaderDelegateItem: MealHeaderDelegateItem? = null

    fun bind(item: MealHeaderDelegateItem) {
        this.mealHeaderDelegateItem = item
        binding.mealTitle.text = binding.root.context.getString(item.mealNameResId)
    }

}