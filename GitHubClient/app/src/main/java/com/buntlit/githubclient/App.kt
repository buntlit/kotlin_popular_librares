package com.buntlit.githubclient

import android.app.Application
import com.buntlit.githubclient.mvp.di.AppComponent
import com.buntlit.githubclient.mvp.di.DaggerAppComponent
import com.buntlit.githubclient.mvp.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
 }