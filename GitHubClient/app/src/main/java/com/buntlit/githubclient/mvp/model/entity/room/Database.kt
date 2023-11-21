package com.buntlit.githubclient.mvp.model.entity.room

import androidx.room.RoomDatabase
import com.buntlit.githubclient.mvp.model.entity.room.dao.ImageDao
import com.buntlit.githubclient.mvp.model.entity.room.dao.RepositoryDao
import com.buntlit.githubclient.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(
    entities = [RoomGitHubUser::class, RoomGitHubRepository::class, RoomGitHubImage::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "database.db"
//        private var instance: Database? = null

//        fun getInstance() = instance ?: throw RuntimeException("Data not created")
//
//        fun create(context: Context) {
//            if (instance == null) {
//                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME).build()
//            }
//        }
    }
}