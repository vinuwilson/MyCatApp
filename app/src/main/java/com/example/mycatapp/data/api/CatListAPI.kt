package com.example.mycatapp.data.api

import com.example.mycatapp.data.model.CatDetails
import retrofit2.http.GET

interface CatListAPI {

    @GET(
        "images/search?" +
                "limit=50&" +
                "has_breeds=1&" +
                "api_key=live_xAGxWK5B7a2f4CiwsKV7B8y9ejHAKqfer8NpHDv890u5lnKqePW23nnu5HxJwKhS"
    )
    suspend fun fetchCatList(): List<CatDetails>
}
