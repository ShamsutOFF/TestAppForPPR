package com.example.testappforppr.di

import android.app.Application
import android.util.Log
import com.example.testappforppr.di.repositoryModule
import com.example.testappforppr.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

private const val TAG = "@@@ MainApp"

class MainApp : Application() {
    override fun onCreate() {
        Log.d(TAG, "onCreate() called")
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(listOf(viewModelModule, repositoryModule))
        }
    }
}