package com.example.mycatapp.catinfo.data.api

import com.example.mycatapp.catinfo.data.model.CatDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatListService @Inject constructor(
    private val api: CatListAPI
) {

    suspend fun getCatList(): Flow<Result<List<CatDetails>>> {
        return flow {
            emit(Result.success(api.fetchCatList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
