package com.mohylov.diet.ui.presentation.mealsList.entities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohylov.diet.R
import com.mohylov.diet.databinding.MealProductItemBinding
import com.mohylov.diet.ui.presentation.mealsList.adapters.adapterDelegate.DelegateAdapter
import com.mohylov.diet.ui.presentation.mealsList.adapters.adapterDelegate.DelegateAdapterItem
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class MealProductDelegateAdapterItem(
    val id: Long,
    val name: String,
    val protein: Float,
    val fats: Float,
    val carbohydrates: Float,
    val calories: Int
) : DelegateAdapterItem {

    override fun id(): Any = id


    override fun content(): Any {
        return protein + fats + carbohydrates + calories
    }

}

class MealProductDelegateAdapter :
    DelegateAdapter<MealProductDelegateAdapterItem, MealProductDelegateAdapter.MealProductViewHolder>(
        MealProductDelegateAdapterItem::class.java
    ) {

    private val _longClickFlow = MutableSharedFlow<MealProductDelegateAdapterItem>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val _clickFlow = MutableSharedFlow<MealProductDelegateAdapterItem>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val longClickFlow: Flow<MealProductDelegateAdapterItem> = _longClickFlow
    val clickFlow: Flow<MealProductDelegateAdapterItem> = _clickFlow

    inner class MealProductViewHolder(
        val binding: MealProductItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        var productItem: MealProductDelegateAdapterItem? = null

        fun bind(productItem: MealProductDelegateAdapterItem) {
            this.productItem = productItem
            val context = binding.root.context
            binding.apply {
                productName.text = productItem.name
                productInfo.carbohydrates.text = context.getString(
                    R.string.carbohydrates, productItem.carbohydrates
                )
                productInfo.fats.text = context.getString(
                    R.string.fats, productItem.fats
                )
                productInfo.calories.text = context.getString(
                    R.string.calories, productItem.calories
                )
                productInfo.proteins.text = context.getString(
                    R.string.proteins, productItem.protein
                )
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): MealProductViewHolder {
        return MealProductViewHolder(
            MealProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            binding.rootContainer.apply {
                setOnLongClickListener {
                    _longClickFlow.tryEmit(productItem ?: return@setOnLongClickListener false)
                }
                setOnClickListener {
                    _clickFlow.tryEmit(productItem ?: return@setOnClickListener)
                }
            }
        }
    }

    override fun bindViewHolder(
        item: MealProductDelegateAdapterItem,
        holder: MealProductViewHolder
    ) {
        holder.bind(item)
    }
}
