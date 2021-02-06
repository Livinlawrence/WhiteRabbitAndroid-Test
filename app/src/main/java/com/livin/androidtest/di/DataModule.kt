package com.livin.androidtest.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.livin.androidtest.data.db.LocalItemsDao
import com.livin.androidtest.data.remote.APIService
import com.livin.androidtest.data.remote.HomeItemsDataSource
import com.livin.androidtest.data.repository.HomeItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://demo.com")//This is for demo
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(APIService: APIService) =
        HomeItemsDataSource(APIService)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: HomeItemsDataSource,
        localDataSource: LocalItemsDao
    ) =
        HomeItemsRepository(remoteDataSource, localDataSource)
}