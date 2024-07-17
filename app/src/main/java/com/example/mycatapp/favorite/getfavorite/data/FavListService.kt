package com.example.mycatapp.favorite.getfavorite.data

import com.example.mycatapp.favorite.getfavorite.data.model.FavDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavListService @Inject constructor(
    private val api: FavListAPI
) {

    suspend fun getFavList(): Flow<Result<List<FavDetails>>> {
        return flow {
            emit(Result.success(api.getFavListAPI()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}