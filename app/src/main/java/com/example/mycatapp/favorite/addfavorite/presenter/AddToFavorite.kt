package com.example.mycatapp.favorite.addfavorite.presenter

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.annotations.SerializedName

@Composable
fun AddToFavorite(catId: String) {

    val viewModel : FavoriteViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.addToFavorite(FavouriteRequest(catId))
    }
    LaunchedEffect(Unit) {
        val result = state.value.favId
        Log.d("TAG", "Count is $result")
    }
}

data class FavouriteRequest(
    @SerializedName("image_id")val imageId: String,
    @SerializedName("sub_id")val subId: String? = null
)

