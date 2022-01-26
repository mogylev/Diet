package com.mohylov.diet.ui.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mohylov.diet.ui.data.product.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAll() : Flow<List<ProductEntity>>

    @Insert(onConflict = REPLACE)
    fun insertAllFoods(foodsList:List<ProductEntity>)

}