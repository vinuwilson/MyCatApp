package com.example.mycatapp.favorite.addfavorite.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycatapp.favorite.addfavorite.domain.FavUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favUseCase: FavUseCase
) : ViewModel() {

    private val _sate = MutableStateFlow(FavUiState())
    val state = _sate.asStateFlow()

    fun addToFavorite(favId: FavouriteRequest) {
        viewModelScope.launch {
            _sate.update {
                it.copy(
                    favId = favUseCase.addToFavorite(favId).first().id
                )
            }
        }
    }
}

data class FavUiState(
    val favId: String = ""
)