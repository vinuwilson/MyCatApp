package com.example.mycatapp.favorite.getfavorite.data

import com.example.mycatapp.BuildConfig.CAT_API_KEY
import com.example.mycatapp.favorite.getfavorite.data.model.FavDetails
import retrofit2.http.GET

interface FavListAPI {

    @GET(
        "favourites?" +
                "limit=50&" +
                "api_key=$CAT_API_KEY"
    )
    suspend fun getFavListAPI(): List<FavDetails>
}