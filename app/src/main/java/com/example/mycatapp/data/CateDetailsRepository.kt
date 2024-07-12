package com.example.mycatapp.data

import com.example.mycatapp.data.model.CatDetails
import com.example.mycatapp.domain.CateDetailsRepository
import kotlinx.coroutines.flow.Flow

class CateDetailsRepositoryImp(
    private val service: CatListService
) : CateDetailsRepository {

    override suspend fun getCatList(): Flow<Result<CatDetails>> {
        return service.getCatList()
    }

}
