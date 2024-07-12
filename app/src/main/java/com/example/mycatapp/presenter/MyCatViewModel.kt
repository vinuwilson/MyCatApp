package com.example.mycatapp.presenter

import androidx.lifecycle.ViewModel
import com.example.mycatapp.data.model.CatDetails
import com.example.mycatapp.domain.CatDetailsUseCase
import kotlinx.coroutines.flow.Flow

class MyCatViewModel(
    private val useCase: CatDetailsUseCase
) : ViewModel() {

    suspend fun getCatList(): Flow<Result<CatDetails>> {
        return useCase.getCatList()
    }

}
