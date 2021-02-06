package com.livin.androidtest.data.remote.models

import com.livin.androidtest.data.db.entities.Banners
import com.livin.androidtest.data.db.entities.Carousels
import com.livin.androidtest.data.db.entities.ItemOrders
import com.livin.androidtest.data.db.entities.Products


data class HomeItemsList(
    val products: MutableList<Products>,
    val banners: MutableList<Banners>,
    val carousels: MutableList<Carousels>,
    val itemOrder: MutableList<ItemOrders>,
)