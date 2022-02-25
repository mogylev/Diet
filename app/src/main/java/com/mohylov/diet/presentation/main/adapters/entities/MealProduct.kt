package com.mohylov.diet.presentation.main.adapters.entities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohylov.diet.R
import com.mohylov.diet.databinding.MealProductItemBinding
import com.mohylov.diet.presentation.main.adapters.adapterDelegate.DelegateAdapter
import com.mohylov.diet.presentation.main.adapters.adapterDelegate.DelegateAdapterItem
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
        return name
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
    val longClickFlow: Flow<MealProductDelegateAdapterItem> = _longClickFlow

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
                productCarbo.text = context.getString(
                    R.string.carbohydrates, productItem.carbohydrates
                )
                productFats.text = context.getString(
                    R.string.fats, productItem.fats
                )
                productKkal.text = context.getString(
                    R.string.calories, productItem.calories
                )
                productProtein.text = context.getString(
                    R.string.proteins, productItem.protein
                )
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): MealProductViewHolder {
        return MealProductViewHolder(
            MealProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            binding.rootContainer.setOnLongClickListener {
                _longClickFlow.tryEmit(productItem ?: return@setOnLongClickListener false)
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
