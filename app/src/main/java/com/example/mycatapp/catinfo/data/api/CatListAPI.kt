package com.example.mycatapp.catinfo.data.api

import com.example.mycatapp.BuildConfig.CAT_API_KEY
import com.example.mycatapp.catinfo.data.model.CatDetails
import retrofit2.http.GET

interface CatListAPI {

    @GET(
        "images/search?" +
                "limit=50&" +
                "has_breeds=1&" +
                "api_key=$CAT_API_KEY"
    )
    suspend fun fetchCatList(): List<CatDetails>
}
