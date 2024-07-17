package com.example.mycatapp.favorite.addfavorite.data

import android.util.Log
import com.example.mycatapp.favorite.addfavorite.presenter.FavouriteRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavService @Inject constructor(
    private val api: FavAPI
) {
    suspend fun addToFavorite(favId: FavouriteRequest): Flow<FavResult> {
        return flow {
            emit(api.addToFavorite(favId))
        }.catch { exp ->
            emit(FavResult(""))
            Log.d("Exception", "$exp.message")
        }
    }
}