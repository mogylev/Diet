package com.mohylov.diet.ui.data.products

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mohylov.diet.ui.data.products.entities.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE name LIKE '%' || :filter || '%'")
    fun getFilteredProducts(filter: String): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :productId")
    fun getProductById(productId: Long): ProductEntity

    @Insert(onConflict = REPLACE)
    fun insertAllFoods(foodsList: List<ProductEntity>)

    @Insert
    fun insertProduct(product: ProductEntity)

}