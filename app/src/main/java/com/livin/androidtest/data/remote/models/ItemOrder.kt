package com.livin.androidtest.data.remote.models

data class ItemOrder(val itemType: Int, val sortOrder: Int) {

    companion object {
        const val ITEM_CAROUSEL = 1
        const val ITEM_BANNER = 2
        const val ITEM_PRODUCT = 3
    }
}