package com.example.mycatapp.domain

import com.example.mycatapp.data.model.CatDetails
import kotlinx.coroutines.flow.Flow

class CatDetailsUseCase(
    private val repository: CateDetailsRepository
) {

    suspend fun getCatList(): Flow<Result<CatDetails>> {
        return repository.getCatList()
    }

}
