package com.mohylov.diet.ui.presentation.mealsList.adapters

import android.view.*
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohylov.diet.R
import com.mohylov.diet.databinding.MealProductItemBinding
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

private val productsDiffCallback = object : DiffUtil.ItemCallback<ProductViewItem>() {

    override fun areItemsTheSame(
        oldItem: ProductViewItem,
        newItem: ProductViewItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ProductViewItem,
        newItem: ProductViewItem
    ): Boolean {
        return oldItem.protein == newItem.protein
    }

}

open class ProductsAdapter :
    ListAdapter<ProductViewItem, ProductsAdapter.MealProductViewHolder>(
        productsDiffCallback
    ) {

    private val _clickFlow = MutableSharedFlow<ProductViewItem>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val clickFlow: Flow<ProductViewItem>
        get() = _clickFlow

    inner class MealProductViewHolder(
        val binding: MealProductItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        var productItem: ProductViewItem? = null

        fun bind(productItem: ProductViewItem) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealProductViewHolder {
        return MealProductViewHolder(
            MealProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            binding.rootContainer.setOnClickListener {
                _clickFlow.tryEmit(productItem ?: return@setOnClickListener)
            }
        }
    }

    override fun onBindViewHolder(holder: MealProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ProductViewItem(
    val id: Long,
    val name: String,
    val protein: Float,
    val fats: Float,
    val carbohydrates: Float,
    val calories: Int
)