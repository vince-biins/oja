package com.project.oja

import android.app.Application
import com.project.oja.di.dashboardModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OjaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@OjaApplication)
            modules(dashboardModule)
        }
    }
}