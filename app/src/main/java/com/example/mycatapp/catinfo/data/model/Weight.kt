package com.example.mycatapp.catinfo.data.model

import com.google.gson.annotations.SerializedName

data class Weight(
    @SerializedName("imperial") val weightInPounds: String,
    @SerializedName("metric") val weightInKg: String
)