package com.mohylov.diet.ui.domain.products

import com.mohylov.diet.ui.domain.products.entities.ProductItem
import javax.inject.Inject

class DefaultFilterStrategy @Inject constructor() : FilterStrategy {

    companion object {
        private const val DEFAULT_LIST_SIZE = 10
    }

    override suspend fun filter(
        filterQueary: String,
        products: List<ProductItem>
    ): List<ProductItem> {
        return if (filterQueary.isBlank()) {
            products.take(DEFAULT_LIST_SIZE)
        } else {
            products.filter { it.productName.contains(filterQueary) }
        }
    }
}