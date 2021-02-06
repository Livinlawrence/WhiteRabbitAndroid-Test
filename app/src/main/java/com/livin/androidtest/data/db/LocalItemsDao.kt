package com.livin.androidtest.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import com.livin.androidtest.data.db.entities.Banners
import com.livin.androidtest.data.db.entities.Carousels
import com.livin.androidtest.data.db.entities.ItemOrders
import com.livin.androidtest.data.db.entities.Products
import com.livin.androidtest.data.remote.models.HomeItemsList

@Dao
interface LocalItemsDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<Products>

    @Query("SELECT * FROM carousels")
    fun getAllCarousels(): List<Carousels>

    @Query("SELECT * FROM banners")
    fun getAllBanners(): List<Banners>

    @Query("SELECT * FROM item_orders")
    fun getItemOrders(): List<ItemOrders>

    fun getAllHomeItems(): LiveData<HomeItemsList> {
        val homeItems = MutableLiveData<HomeItemsList>()
        // need to write return query
        return homeItems
    }

    fun insertAll(it: HomeItemsList) {
        // need to write insert query
    }
}