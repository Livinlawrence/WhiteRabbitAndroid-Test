package com.livin.androidtest.data.remote

import com.livin.androidtest.data.db.entities.Banners
import com.livin.androidtest.data.db.entities.Carousels
import com.livin.androidtest.data.db.entities.ItemOrders
import com.livin.androidtest.data.db.entities.Products
import com.livin.androidtest.data.remote.models.HomeItemsList
import com.livin.androidtest.data.remote.models.ItemOrder.Companion.ITEM_BANNER
import com.livin.androidtest.data.remote.models.ItemOrder.Companion.ITEM_CAROUSEL
import com.livin.androidtest.data.remote.models.ItemOrder.Companion.ITEM_PRODUCT
import com.livin.androidtest.utils.Resource
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
abstract class BaseDataSource {

    /**
     * Executes the api service and returns the result
     */
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        /* try {
             val response = call()
             if (response.isSuccessful) {
                 val body = response.body()
                 if (body != null) return Resource.success(body)
             }
             return error(" ${response.code()} ${response.message()}")
         } catch (e: Exception) {
             return error(e.message ?: e.toString())
         }*/

        //By passing mock list
        return Resource.success(mockResponse())
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network call has failed for a following reason: $message")
    }


    /**
     * This is to demo the data in ui instead of consuming a api
     */
    private fun <T> mockResponse(): T {
        val products = mutableListOf<Products>()
        val banners = mutableListOf<Banners>()
        val carousels = mutableListOf<Carousels>()
        val itemOrder = mutableListOf<ItemOrders>()


        products.add(
            Products(
                id = 1,
                thumb = "https://tinyurl.com/y4f5e96j",
                name = "Sample Title"
            )
        )
        products.add(
            Products(
                id = 2,
                thumb = "https://tinyurl.com/y2szwrys",
                name = "Sample Title"
            )
        )
        products.add(
            Products(
                id = 3,
                thumb = "https://tinyurl.com/y4bfj5b7",
                discount = 20.5,
                name = "Sample Title"
            )
        )
        products.add(
            Products(
                id = 4,
                thumb = "https://tinyurl.com/y44marw5",
                name = "Sample Title"
            )
        )
        products.add(
            Products(
                id = 5,
                thumb = "https://tinyurl.com/y4urobx8",
                discount = 60.5,
                name = "Sample Title"
            )
        )
        products.add(
            Products(
                id = 6,
                thumb = "https://tinyurl.com/y2yhf95n",
                name = "Sample Title"
            )
        )
        products.add(
            Products(
                id = 7,
                thumb = "https://tinyurl.com/y5n467o3",
                discount = 100.0,
                name = "Sample Title"
            )
        )
        products.add(
            Products(
                id = 8,
                thumb = "https://tinyurl.com/yxupqdll",
                name = "Sample Title"
            )
        )

        banners.add(Banners(id = 1, image = "https://tinyurl.com/y379jw6s"))
        banners.add(Banners(id = 2, image = "https://tinyurl.com/y3pjtea4"))
        banners.add(Banners(id = 3, image = "https://tinyurl.com/y2gersqn"))
        banners.add(Banners(id = 4, image = "https://tinyurl.com/y3c6ksu5"))
        banners.add(Banners(id = 5, image = "https://tinyurl.com/y4k2klen"))
        banners.add(Banners(id = 6, image = "https://tinyurl.com/y3pccdrc"))
        banners.add(Banners(id = 7, image = "https://tinyurl.com/y26fn9rm"))

        carousels.add(Carousels(id = 7, image = "https://tinyurl.com/y3w8oaah"))
        carousels.add(Carousels(id = 7, image = "https://tinyurl.com/y4vaulog"))
        carousels.add(Carousels(id = 7, image = "https://tinyurl.com/y3j7rq6g"))
        carousels.add(Carousels(id = 7, image = "https://tinyurl.com/y28jpmyr"))
        carousels.add(Carousels(id = 7, image = "https://tinyurl.com/y2w7fbdo"))
        carousels.add(Carousels(id = 7, image = "https://tinyurl.com/yy2f6lha"))

        itemOrder.add(ItemOrders(id = 1, itemType = ITEM_CAROUSEL, sortOrder = 1))
        itemOrder.add(ItemOrders(id = 3, itemType = ITEM_PRODUCT, sortOrder = 3))
        itemOrder.add(ItemOrders(id = 2, itemType = ITEM_BANNER, sortOrder = 2))

        return HomeItemsList(products, banners, carousels, itemOrder) as T
    }
}