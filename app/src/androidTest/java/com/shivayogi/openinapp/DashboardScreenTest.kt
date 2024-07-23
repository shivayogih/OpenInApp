package com.shivayogi.openinapp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shivayogi.openinapp.ui.components.MainActivity
import com.shivayogi.openinapp.ui.screen.home.DashboardScreen
import com.shivayogi.openinapp.ui.theme.DashboardTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DashboardScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testDashboardScreenDisplaysContent() {
        composeTestRule.setContent {
            DashboardTheme {
                DashboardScreen()
            }
        }

        composeTestRule.onNodeWithText("Dashboard").assertExists()
        composeTestRule.onNodeWithText("Links").assertExists()
        composeTestRule.onNodeWithText("Courses").assertExists()
    }
}
