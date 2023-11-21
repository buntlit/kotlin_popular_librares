package com.buntlit.githubclient.mvp.di.modules

import androidx.room.Room
import com.buntlit.githubclient.App
import com.buntlit.githubclient.mvp.model.cache.IGitHubImagesCache
import com.buntlit.githubclient.mvp.model.cache.IGitHubRepositoriesCache
import com.buntlit.githubclient.mvp.model.cache.IGithubUsersCache
import com.buntlit.githubclient.mvp.model.cache.room.RoomGitHubImagesCache
import com.buntlit.githubclient.mvp.model.cache.room.RoomGitHubRepositoriesCache
import com.buntlit.githubclient.mvp.model.cache.room.RoomGitHubUsersCache
import com.buntlit.githubclient.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Provides
    @Singleton
    fun usersCache(database: Database): IGithubUsersCache {
       return RoomGitHubUsersCache(database)
    }

    @Provides
    @Singleton
    fun repositoriesCache(database: Database): IGitHubRepositoriesCache {
        return RoomGitHubRepositoriesCache(database)
    }

    @Provides
    @Singleton
    fun imagesCache(database: Database): IGitHubImagesCache {
        return RoomGitHubImagesCache(database)
    }
}