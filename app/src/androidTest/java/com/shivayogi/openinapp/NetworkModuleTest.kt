package com.shivayogi.openinapp

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shivayogi.openinapp.data.api.ApiService
import com.shivayogi.openinapp.data.local.TokenManager
import com.shivayogi.openinapp.data.local.TokenManager.TOKEN_VALUE
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NetworkModuleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var context: Context

    @Before
    fun init() {
        hiltRule.inject()
        TokenManager.initialize(context)
    }

    @Test
    fun testAuthInterceptor_withToken() {
        val mockWebServer = MockWebServer()
        mockWebServer.start()

        val token = TOKEN_VALUE
        TokenManager.saveToken(token)

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody("{}"))

        runBlocking {
            val response = apiService.getDashboardData()
            assertNotNull(response)
        }

        val request = mockWebServer.takeRequest()
        assertEquals("Bearer $token", request.getHeader("Authorization"))

        mockWebServer.shutdown()
    }
}
