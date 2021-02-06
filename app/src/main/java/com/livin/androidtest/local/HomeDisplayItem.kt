package com.livin.androidtest.local

import com.livin.androidtest.data.remote.models.Banner
import com.livin.androidtest.data.remote.models.Carousel
import com.livin.androidtest.data.remote.models.Product

data class HomeDisplayItem(
    val products: MutableList<Product>? = null,
    val carousels: MutableList<Carousel>? = null,
    val banner: Banner? = null,
    val type: Int
)