package com.shivayogi.openinapp

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import com.shivayogi.openinapp.data.local.TokenManager
import com.google.common.truth.Truth.assertThat
import com.shivayogi.openinapp.data.local.TokenManager.TOKEN_VALUE
import org.junit.Before
import org.junit.Test
import org.robolectric.RobolectricTestRunner
import org.junit.runner.RunWith

@RunWith(RobolectricTestRunner::class)
class TokenManagerTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        TokenManager.initialize(context)
    }

    @Test
    fun saveToken_savesTokenCorrectly() {
        val token = TOKEN_VALUE

        TokenManager.saveToken( token)

        val savedToken = TokenManager.getToken()
        assertThat(savedToken).isEqualTo(token)
    }

    @Test
    fun getToken_returnsNullWhenNoTokenSaved() {
        val token = TokenManager.getToken()
        assertThat(token).isNull()
    }
}

