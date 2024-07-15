package com.example.mycatapp.domain

import com.example.mycatapp.data.model.CatDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatDetailsUseCase @Inject constructor(
    private val repository: CateDetailsRepository
) {

    suspend fun getCatList(): Flow<Result<List<CatDetails>>> {
        return repository.getCatList()
    }

}
