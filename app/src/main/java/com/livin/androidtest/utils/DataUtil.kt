package com.livin.androidtest.utils

import com.livin.androidtest.data.db.entities.Banners
import com.livin.androidtest.data.db.entities.Carousels
import com.livin.androidtest.data.db.entities.Products
import com.livin.androidtest.data.remote.models.Banner
import com.livin.androidtest.data.remote.models.Carousel
import com.livin.androidtest.data.remote.models.HomeItemsList
import com.livin.androidtest.data.remote.models.ItemOrder.Companion.ITEM_BANNER
import com.livin.androidtest.data.remote.models.ItemOrder.Companion.ITEM_CAROUSEL
import com.livin.androidtest.data.remote.models.ItemOrder.Companion.ITEM_PRODUCT
import com.livin.androidtest.data.remote.models.Product
import com.livin.androidtest.local.HomeDisplayItem

object DataUtil {

    /**
     * Manipulate raw/entity data to accomodate business logic
     */
    fun manipulateHomeItems(homeList: HomeItemsList): MutableList<HomeDisplayItem> {
        val homeDisplayItems = mutableListOf<HomeDisplayItem>()
        homeList.itemOrder.sortBy { it.sortOrder }
        homeList.itemOrder.forEach {
            when (it.itemType) {
                ITEM_PRODUCT -> {
                    homeDisplayItems.add(getProductFromEntity(homeList.products))
                }
                ITEM_BANNER -> {
                    homeDisplayItems.add(getBannerFromEntity(homeList.banners))
                }
                ITEM_CAROUSEL -> {
                    homeDisplayItems.add(getCarouselFromEntity(homeList.carousels))
                }
            }
        }

        return homeDisplayItems
    }

    private fun getProductFromEntity(products: MutableList<Products>): HomeDisplayItem {
        products.sortBy { it.priority }
        val prodList: MutableList<Product> = mutableListOf()
        products.forEach {
            prodList.add(
                Product(
                    id = it.id,
                    name = it.name,
                    thumb = it.thumb,
                    discount = it.discount
                )
            )
        }
        return HomeDisplayItem(products = prodList, type = ITEM_PRODUCT)
    }

    private fun getBannerFromEntity(banners: MutableList<Banners>): HomeDisplayItem {
        val bannersList: MutableList<Banner> = mutableListOf()
        banners.forEach {
            bannersList.add(
                Banner(
                    id = it.id,
                    image = it.image
                )
            )
        }
        return HomeDisplayItem(banner = bannersList[0], type = ITEM_BANNER)
    }

    private fun getCarouselFromEntity(carouselsEntityList: MutableList<Carousels>): HomeDisplayItem {
        val carousels: MutableList<Carousel> = mutableListOf()
        carouselsEntityList.forEach {
            carousels.add(
                Carousel(
                    id = it.id,
                    image = it.image
                )
            )
        }
        return HomeDisplayItem(carousels = carousels, type = ITEM_CAROUSEL)
    }
}