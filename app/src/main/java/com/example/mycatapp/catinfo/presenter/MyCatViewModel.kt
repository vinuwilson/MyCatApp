package com.example.mycatapp.catinfo.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycatapp.catinfo.data.model.CatDetails
import com.example.mycatapp.catinfo.domain.CatDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCatViewModel @Inject constructor(
    private val useCase: CatDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CatViewModelState())
    val state = _state.asStateFlow()

    fun getAllCatList() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loading = true
                )
            }
            _state.update {
                it.copy(
                    catDetails = getCatList().first().getOrNull(),
                    loading = false
                )
            }
        }
    }

    suspend fun getCatList(): Flow<Result<List<CatDetails>>> {
        return useCase.getCatList()
    }

}
