package com.example.mycatapp.data.model

import com.google.gson.annotations.SerializedName

data class CatDetailsItem(
    @SerializedName("breeds") val listOfBreeds: List<Breed>,
    val height: Int,
    @SerializedName("id") val catId: String,
    @SerializedName("url") val catImageUrl: String,
    val width: Int
)