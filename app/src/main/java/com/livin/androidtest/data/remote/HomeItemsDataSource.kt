package com.livin.androidtest.data.remote

import javax.inject.Inject

class HomeItemsDataSource @Inject constructor(
    private val APIService: APIService
) : BaseDataSource() {

    suspend fun getAllHomeItems() = getResult { APIService.getAllHomeItems() }
}