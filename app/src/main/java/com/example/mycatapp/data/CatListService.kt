package com.example.mycatapp.data

import com.example.mycatapp.data.model.CatDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CatListService(
    private val api: CatListAPI
) {

    suspend fun getCatList(): Flow<Result<CatDetails>> {
        return flow {
            emit(Result.success(api.fetchCatList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
