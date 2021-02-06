package com.livin.androidtest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livin.androidtest.data.db.entities.Carousels

@Dao
interface CarouselDao {

    @Query("SELECT * FROM carousels")
    fun getAllCarousels(): LiveData<List<Carousels>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: MutableList<Carousels>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: Carousels)

}