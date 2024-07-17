package com.example.mycatapp.favorite.getfavorite.domain

import com.example.mycatapp.favorite.getfavorite.data.model.FavDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavListUseCase @Inject constructor(
    private val favListRepository: FavListRepository
) {

    suspend fun getFavList(): Flow<Result<List<FavDetails>>> {
        return favListRepository.getFavList()
    }
}