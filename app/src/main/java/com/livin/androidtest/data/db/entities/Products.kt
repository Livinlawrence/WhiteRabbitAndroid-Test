package com.livin.androidtest.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Products(
    @PrimaryKey
    val id: Int,
    val sku: String? = null,
    val name: String? = null,
    val description: String? = null,
    val pages: Int? = null,
    val permalink: String? = null,
    val price: String? = null,
    val displayPrice: String? = null,
    val regularPrice: String? = null,
    val salePrice: String? = null,
    val onSale: Boolean? = null,
    val inStock: Boolean? = null,
    val averageRating: Double? = null,
    val discount: Double? = null,
    val ratingCount: Int? = null,
    val categoryId: String? = null,
    val stockQuantity: Int? = null,
    val thumb: String? = null,
    val minQuantity: Int? = null,
    val maxQuantity: Int? = null,
    val priority: Int? = null
)