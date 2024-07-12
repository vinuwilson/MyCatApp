package com.example.mycatapp.domain

import com.example.mycatapp.data.model.CatDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatDetailsUseCase {

    fun getCatDetailsList(): Flow<Result<CatDetails>> {
        return flow { }
    }

}
