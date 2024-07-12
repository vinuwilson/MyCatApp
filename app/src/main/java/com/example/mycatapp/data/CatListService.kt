package com.example.mycatapp.data

import com.example.mycatapp.data.model.CatDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatListService {

    suspend fun getCatList(): Flow<Result<CatDetails>> {
        return flow { }
    }

}
