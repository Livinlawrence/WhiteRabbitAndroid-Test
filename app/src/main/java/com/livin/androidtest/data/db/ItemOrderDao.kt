package com.livin.androidtest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livin.androidtest.data.db.entities.ItemOrders

@Dao
interface ItemOrderDao {

    @Query("SELECT * FROM item_orders")
    fun getItemOrders(): LiveData<List<ItemOrders>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(itemOrders: MutableList<ItemOrders>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemOrders: ItemOrders)

}