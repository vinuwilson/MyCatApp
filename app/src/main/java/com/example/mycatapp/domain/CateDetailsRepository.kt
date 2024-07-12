package com.example.mycatapp.domain

import com.example.mycatapp.data.model.CatDetails
import kotlinx.coroutines.flow.Flow

interface CateDetailsRepository {

    suspend fun getCatList() : Flow<Result<CatDetails>>
}
