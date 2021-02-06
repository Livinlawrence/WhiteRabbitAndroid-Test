package com.livin.androidtest.data.remote

import com.livin.androidtest.data.remote.models.HomeItemsList
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("homeItems")
    suspend fun getAllHomeItems(): Response<HomeItemsList>
}