package com.buntlit.githubclient

import android.app.Application
import com.buntlit.githubclient.mvp.model.entity.room.Database
import com.github.terrakok.cicerone.Cicerone

class App : Application() {

    private val cicerone = Cicerone.create()

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        Database.create(this)
    }

    val navigatorHolder
        get() = cicerone.getNavigatorHolder()

    val router
        get() = cicerone.router
}