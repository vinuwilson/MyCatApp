package com.example.mycatapp.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class SavedFevPreferences @Inject constructor(
    @ApplicationContext context: Context
) {
    private val appContext = context.applicationContext

    suspend fun saveUserPreference(imageId: String, flag: Boolean) {
        appContext.dataStore.edit { pref ->
            pref[IMAGE_ID] = imageId
            pref[FAV_FLAG] = flag
        }
    }

    val getImageID: Flow<String> = appContext.dataStore.data.map { pref ->
        pref[IMAGE_ID] ?: "abcxyz"
    }

    val getFavStatus: Flow<Boolean> = appContext.dataStore.data.map { pref ->
        pref[FAV_FLAG] ?: false
    }

    companion object {
        private val IMAGE_ID = stringPreferencesKey("key_image_id")
        private val FAV_FLAG = booleanPreferencesKey("key_fav_flag")
    }
}