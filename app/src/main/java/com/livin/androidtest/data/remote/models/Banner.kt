package com.livin.androidtest.data.remote.models

import com.livin.androidtest.local.HomeItem

data class Banner(
    val id: Int,
    val categoryId: Int? = null,
    val storeId: Int? = null,
    val productId: Int? = null,
    val image: String? = null
) : HomeItem()