package com.example.mycatapp.catinfo.data.di

import com.example.mycatapp.catinfo.data.api.CatListAPI
import com.example.mycatapp.catinfo.data.api.CatListService
import com.example.mycatapp.catinfo.data.api.CateDetailsRepositoryImp
import com.example.mycatapp.catinfo.domain.CateDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class CatModule {

    @Provides
    fun getCatList(retrofit: Retrofit): CatListAPI = retrofit.create(CatListAPI::class.java)

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideRepository(service: CatListService): CateDetailsRepository = CateDetailsRepositoryImp(service)
}