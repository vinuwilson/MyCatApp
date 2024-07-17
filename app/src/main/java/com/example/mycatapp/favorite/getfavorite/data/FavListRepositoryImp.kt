package com.example.mycatapp.favorite.getfavorite.data

import com.example.mycatapp.favorite.getfavorite.data.model.FavDetails
import com.example.mycatapp.favorite.getfavorite.domain.FavListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavListRepositoryImp @Inject constructor(
    private val service: FavListService
) : FavListRepository {

    override suspend fun getFavList(): Flow<Result<List<FavDetails>>> {
        return service.getFavList()
    }
}