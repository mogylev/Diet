package com.mohylov.diet.ui.presentation.mealsList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohylov.diet.R
import com.mohylov.diet.databinding.MealProductItemBinding
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow


class ProductsAdapter :
    ListAdapter<ProductViewItem, ProductsAdapter.MealProductViewHolder>(
        mealProductsDiffCallback
    ) {

    val clickFlow = MutableSharedFlow<ProductViewItem>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val longClickFlow = MutableSharedFlow<ProductViewItem>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealProductViewHolder {
        return MealProductViewHolder(
            MealProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            binding.rootContainer.setOnClickListener {
                clickFlow.tryEmit(productItem ?: return@setOnClickListener)
            }
            binding.rootContainer.setOnLongClickListener {
                longClickFlow.tryEmit(productItem ?: return@setOnLongClickListener false)
            }
        }
    }

    override fun onBindViewHolder(holder: MealProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val mealProductsDiffCallback = object : DiffUtil.ItemCallback<ProductViewItem>() {

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