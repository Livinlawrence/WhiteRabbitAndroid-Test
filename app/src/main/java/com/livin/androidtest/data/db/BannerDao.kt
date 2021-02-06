package com.livin.androidtest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livin.androidtest.data.db.entities.Banners

@Dao
interface BannerDao {

    @Query("SELECT * FROM banners")
    fun getAllBanners(): LiveData<List<Banners>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(banners: MutableList<Banners>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(banners: Banners)


}