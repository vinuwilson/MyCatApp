package com.example.mycatapp.favorite.getfavorite.domain

import com.example.mycatapp.favorite.getfavorite.data.model.FavDetails
import kotlinx.coroutines.flow.Flow

interface FavListRepository {

    suspend fun getFavList(): Flow<Result<List<FavDetails>>>
}