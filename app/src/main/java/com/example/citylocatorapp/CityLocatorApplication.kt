package com.example.citylocatorapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CityLocatorApplication :Application() {
    override fun onCreate() {
        super.onCreate()
    }
}