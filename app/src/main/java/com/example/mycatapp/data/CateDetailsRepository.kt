package com.example.mycatapp.data

import com.example.mycatapp.data.model.CatDetails
import com.example.mycatapp.domain.CateDetailsRepository
import kotlinx.coroutines.flow.Flow

class CateDetailsRepositoryImp :CateDetailsRepository {
    override suspend fun getCatList(): Flow<Result<CatDetails>> {
        TODO("Not yet implemented")
    }

}
