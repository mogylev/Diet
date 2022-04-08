package com.mohylov.diet.ui.presentation.productsManagement.personalProducts.adapters

import android.view.Gravity
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.mohylov.diet.R
import com.mohylov.diet.ui.presentation.mealsList.adapters.ProductViewItem
import com.mohylov.diet.ui.presentation.mealsList.adapters.ProductsAdapter
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class PersonalProductsAdapter : ProductsAdapter() {

    private val _popupMenuEventFlow = MutableSharedFlow<Map<PopUpMenuItem, ProductViewItem>>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val popupMenuClickFlow: Flow<Map<PopUpMenuItem, ProductViewItem>>
        get() = _popupMenuEventFlow

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealProductViewHolder {
        return super.onCreateViewHolder(parent, viewType).apply {
            binding.rootContainer.setOnLongClickListener {
                productItem?.let { }
                with(productItem) {
                    if (this == null) return@setOnLongClickListener false
                    showPopupMenu(binding.root, this)
                    true
                }
            }
        }
    }

    private fun showPopupMenu(view: View, productItem: ProductViewItem) {
        val popUp = PopupMenu(view.context, view, Gravity.END)
        val inflate: MenuInflater = popUp.menuInflater
        inflate.inflate(R.menu.product_popup_menu, popUp.menu)
        popUp.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.remove_menu -> {
                    _popupMenuEventFlow.tryEmit(mapOf(PopUpMenuItem.Delete to productItem))
                }
            }
            true
        }
        popUp.show()
    }
}

sealed class PopUpMenuItem {
    object Delete : PopUpMenuItem()
}