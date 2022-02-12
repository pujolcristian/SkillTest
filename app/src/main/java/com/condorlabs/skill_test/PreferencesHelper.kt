package com.condorlabs.skill_test

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveAccessToken(token: String) {
        sharedPreferences.edit {
            putString(KEY_TOKEN, token)
        }
    }

    fun getAccessToken(): String {
        val data = sharedPreferences.getString(KEY_TOKEN, "asdfghjkl")
        return if (data.isNullOrEmpty()) {
            ""
        } else {
            try {
                data
            } catch (e: Exception) {
                ""
            }
        }
    }

    companion object {
        private const val KEY_TOKEN = "HUig9T23UF872/ÑBKvahY+AjfIU"
    }
}