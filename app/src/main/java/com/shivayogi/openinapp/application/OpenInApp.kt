package com.shivayogi.openinapp.application

import android.app.Application
import com.shivayogi.openinapp.data.local.TokenManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OpenInApp : Application() {


    override fun onCreate() {
        super.onCreate()
        TokenManager.initialize(this)
    }
}