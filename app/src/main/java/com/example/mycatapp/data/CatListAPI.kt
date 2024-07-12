package com.example.mycatapp.data

import com.example.mycatapp.data.model.CatDetails

interface CatListAPI {

    suspend fun fetchCatList() : CatDetails
}
