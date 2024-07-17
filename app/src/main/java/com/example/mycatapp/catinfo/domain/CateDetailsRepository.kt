package com.example.mycatapp.catinfo.domain

import com.example.mycatapp.catinfo.data.model.CatDetails
import kotlinx.coroutines.flow.Flow

interface CateDetailsRepository {

    suspend fun getCatList() : Flow<Result<List<CatDetails>>>
}
