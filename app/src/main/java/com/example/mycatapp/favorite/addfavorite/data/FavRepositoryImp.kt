package com.example.mycatapp.favorite.addfavorite.data

import com.example.mycatapp.favorite.addfavorite.domain.FavRepository
import com.example.mycatapp.favorite.addfavorite.presenter.FavouriteRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavRepositoryImp @Inject constructor(
    private val favService: FavService
) : FavRepository {

    override suspend fun addToFavorite(favId: FavouriteRequest): Flow<FavResult> {
        return favService.addToFavorite(favId)
    }
}