package com.heyproject.storyapp

import android.app.Application
import com.heyproject.storyapp.di.InjectionModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
Written by Yayan Rahmat Wijaya on 9/23/2022 10:41
Github : https://github.com/yayanrw
 **/

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(InjectionModules.getModules())
        }
    }
}