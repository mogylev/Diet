package com.mohylov.diet.ui.domain.products.personal

import com.mohylov.diet.ui.data.products.ProductsRepository
import com.mohylov.diet.ui.domain.products.entities.ProductItem
import javax.inject.Inject

class PersonalProductsInteractorImpl @Inject constructor(private val productsRepository: ProductsRepository) :
    PersonalProductsInteractor {

    override suspend fun getPersonalProducts(): List<ProductItem> {
        return productsRepository.getProducts().filter { it.removable }
    }

    override suspend fun searchPersonalProducts(searchFilter: String): List<ProductItem> {
        return getPersonalProducts().filter { it.productName.contains(searchFilter) }
    }
}