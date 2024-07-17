package com.example.mycatapp.favorite.addfavorite.data

import com.example.mycatapp.BuildConfig.CAT_API_KEY
import com.example.mycatapp.favorite.addfavorite.presenter.FavouriteRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FavAPI {

    @Headers("x-api-key:$CAT_API_KEY")
    @POST("favourites")
    suspend fun addToFavorite(@Body favouriteRequest: FavouriteRequest): FavResult
}