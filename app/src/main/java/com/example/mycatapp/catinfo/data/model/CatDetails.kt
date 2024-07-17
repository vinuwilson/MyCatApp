package com.example.mycatapp.catinfo.data.model

import com.google.gson.annotations.SerializedName

data class CatDetails(
    @SerializedName("breeds") val listOfBreeds: List<Breed>?,
    @SerializedName("id") val catId: String,
    @SerializedName("url") val catImageUrl: String
)