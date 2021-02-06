package com.livin.androidtest.di

import android.content.Context
import com.livin.androidtest.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideProductDao(db: AppDatabase) = db.productDao()

    @Singleton
    @Provides
    fun provideBannerDao(db: AppDatabase) = db.bannerDao()

    @Singleton
    @Provides
    fun provideCarouselDao(db: AppDatabase) = db.carouselDao()

    @Singleton
    @Provides
    fun provideLocalDao(db: AppDatabase) = db.localDao()

}