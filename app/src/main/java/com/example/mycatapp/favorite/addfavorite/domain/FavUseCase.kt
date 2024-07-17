package com.example.mycatapp.favorite.addfavorite.domain

import com.example.mycatapp.favorite.addfavorite.data.FavResult
import com.example.mycatapp.favorite.addfavorite.presenter.FavouriteRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavUseCase @Inject constructor(
    private val favRepository: FavRepository
) {
    suspend fun addToFavorite(favId: FavouriteRequest): Flow<FavResult>{
        return favRepository.addToFavorite(favId)
    }
}