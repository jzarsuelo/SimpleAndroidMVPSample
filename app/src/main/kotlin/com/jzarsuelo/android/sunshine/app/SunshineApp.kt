package com.jzarsuelo.android.sunshine.app

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.jzarsuelo.android.sunshine.BuildConfig

class SunshineApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SunshineApp.applicationContext = applicationContext

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    companion object {
        private var applicationContext: Context? = null
        fun getAppContext() = SunshineApp.applicationContext!!
    }
}