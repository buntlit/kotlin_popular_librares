package com.buntlit.githubclient.mvp.model.entity.room

import androidx.room.RoomDatabase
import com.buntlit.githubclient.mvp.model.entity.room.dao.ImageDao
import com.buntlit.githubclient.mvp.model.entity.room.dao.RepositoryDao
import com.buntlit.githubclient.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(
    entities = [
        RoomGitHubUser::class,
        RoomGitHubRepository::class,
        RoomGitHubImage::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "database.db"
    }
}