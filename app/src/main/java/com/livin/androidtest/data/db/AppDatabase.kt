package com.livin.androidtest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.livin.androidtest.data.db.entities.Banners
import com.livin.androidtest.data.db.entities.Carousels
import com.livin.androidtest.data.db.entities.ItemOrders
import com.livin.androidtest.data.db.entities.Products

@Database(
    entities = [Carousels::class, Banners::class, Products::class, ItemOrders::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun bannerDao(): BannerDao
    abstract fun carouselDao(): CarouselDao
    abstract fun localDao(): LocalItemsDao

    companion object {
        private const val DB_NAME = "shop_db"
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

}