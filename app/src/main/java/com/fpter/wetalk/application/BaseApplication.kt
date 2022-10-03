package com.fpter.wetalk.application

import android.app.Application
import timber.log.Timber

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLog()
    }

    protected abstract fun isEnableLogCat(): Boolean

    protected abstract fun getAppName(): String

    private fun initLog(){
        if(isEnableLogCat()) {
            Timber.plant(Timber.DebugTree())
        }
    }
}