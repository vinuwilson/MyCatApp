package com.example.mycatapp.favorite.addfavorite.data

import com.example.mycatapp.favorite.addfavorite.domain.FavRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class FavDI {

    @Provides
    fun addToFav(retrofit: Retrofit): FavAPI = retrofit.create(FavAPI::class.java)

    @Provides
    fun provideFavRepository(service: FavService): FavRepository = FavRepositoryImp(service)

}