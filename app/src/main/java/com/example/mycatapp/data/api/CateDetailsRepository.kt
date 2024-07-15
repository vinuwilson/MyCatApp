package com.example.mycatapp.data.api

import com.example.mycatapp.data.model.CatDetails
import com.example.mycatapp.domain.CateDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CateDetailsRepositoryImp @Inject constructor(
    private val service: CatListService
) : CateDetailsRepository {

    override suspend fun getCatList(): Flow<Result<List<CatDetails>>> {
        return service.getCatList()
    }

}
