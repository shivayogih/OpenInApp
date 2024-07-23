package com.shivayogi.openinapp.data.local

import android.content.Context
import android.content.SharedPreferences

object TokenManager {
    private const val PREF_NAME = "token_prefs"
    const val TOKEN_KEY = "bearer_token"
    const val TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"

    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        checkInitialization()
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun getToken(): String? {
        checkInitialization()
        return sharedPreferences.getString(TOKEN_KEY, null)
    }

    fun clearToken() {
        checkInitialization()
        val editor = sharedPreferences.edit()
        editor.remove(TOKEN_KEY)
        editor.apply()
    }

    private fun checkInitialization() {
        if (!::sharedPreferences.isInitialized) {
            throw IllegalStateException("TokenManager is not initialized. Call initialize() with a context before using.")
        }
    }
}
