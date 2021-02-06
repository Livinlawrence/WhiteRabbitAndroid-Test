package com.livin.androidtest.data.repository

import com.livin.androidtest.data.db.LocalItemsDao
import com.livin.androidtest.data.remote.HomeItemsDataSource
import com.livin.androidtest.utils.performGetOperation
import javax.inject.Inject

class HomeItemsRepository @Inject constructor(
    private val remoteDataSource: HomeItemsDataSource,
    private val localDataSource: LocalItemsDao
) {

    fun getAllHomeItems() = performGetOperation(
        databaseQuery = { localDataSource.getAllHomeItems() },
        networkCall = { remoteDataSource.getAllHomeItems() },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}