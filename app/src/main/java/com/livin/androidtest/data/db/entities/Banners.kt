package com.livin.androidtest.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banners")
data class Banners(
    @PrimaryKey
    val id: Int,
    val categoryId: Int? = null,
    val storeId: Int? = null,
    val productId: Int? = null,
    val image: String? = null
)