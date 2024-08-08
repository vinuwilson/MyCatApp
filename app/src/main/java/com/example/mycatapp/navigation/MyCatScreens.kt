package com.example.mycatapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object CatListScreen

@Serializable
data class CatDetailsScreen(
    val catId: String,
)

@Serializable
object CatInformation

@Serializable
object FavoriteListScreen