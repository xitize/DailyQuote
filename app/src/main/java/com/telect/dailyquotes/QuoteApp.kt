package com.telect.dailyquotes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuoteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize any global resources here
    }
}