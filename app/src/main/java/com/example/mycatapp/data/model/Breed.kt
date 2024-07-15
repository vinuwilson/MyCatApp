package com.example.mycatapp.data.model

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("country_code") val countryCode: String,
    val description: String,
    @SerializedName("id") val breedId: String,
    @SerializedName("life_span") val lifeSpan: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val weight: Weight
)