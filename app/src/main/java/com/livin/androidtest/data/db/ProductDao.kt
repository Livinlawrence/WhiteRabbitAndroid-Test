package com.livin.androidtest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livin.androidtest.data.db.entities.Products

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<Products>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: MutableList<Products>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: Products)

}