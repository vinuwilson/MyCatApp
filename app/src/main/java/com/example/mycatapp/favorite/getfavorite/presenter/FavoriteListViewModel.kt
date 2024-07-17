package com.example.mycatapp.favorite.getfavorite.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycatapp.favorite.getfavorite.data.model.FavDetails
import com.example.mycatapp.favorite.getfavorite.domain.FavListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val favListUseCase: FavListUseCase
) : ViewModel() {

    private val _sate = MutableStateFlow(FavListUiState())
    val state = _sate.asStateFlow()

    fun getFavorite() {
        viewModelScope.launch {
            _sate.update {
                it.copy(
                    loading = true
                )
            }
        }
        viewModelScope.launch {
            _sate.update {
                it.copy(
                    favDetails = favListUseCase.getFavList().first().getOrNull()!!,
                    loading = false
                )
            }
        }
    }
}

data class FavListUiState(
    val favDetails: List<FavDetails> = emptyList(),
    val loading: Boolean = false
)