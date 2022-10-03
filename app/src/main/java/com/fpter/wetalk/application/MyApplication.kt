package com.fpter.wetalk.application

import com.fpter.wetalk.R
import com.fpter.wetalk.application.injection.allModules
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : BaseApplication() {



    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(allModules)
        }
    }

    override fun isEnableLogCat(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun getAppName(): String {
        return getString(R.string.app_name)
    }

}