package com.example.mycatapp.favorite.getfavorite.data

import com.example.mycatapp.favorite.getfavorite.domain.FavListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class FavListModule {

    @Provides
    fun favList(retrofit: Retrofit): FavListAPI = retrofit.create(FavListAPI::class.java)

    @Provides
    fun provideFavListRepository(service: FavListService): FavListRepository = FavListRepositoryImp(service)
}