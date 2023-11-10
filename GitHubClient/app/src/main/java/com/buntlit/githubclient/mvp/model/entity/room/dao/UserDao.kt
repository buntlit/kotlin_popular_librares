package com.buntlit.githubclient.mvp.model.entity.room.dao

import androidx.room.*
import com.buntlit.githubclient.mvp.model.entity.room.RoomGitHubUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGitHubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGitHubUser>)

    @Update
    fun update(user: RoomGitHubUser)

    @Update
    fun update(vararg users: RoomGitHubUser)

    @Update
    fun update(users: List<RoomGitHubUser>)

    @Delete
    fun delete(user: RoomGitHubUser)

    @Delete
    fun delete(vararg users: RoomGitHubUser)

    @Delete
    fun delete(users: List<RoomGitHubUser>)

    @Query("SELECT * FROM RoomGitHubUser")
    fun getAll(): List<RoomGitHubUser>
    @Query("SELECT * FROM RoomGitHubUser WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGitHubUser
}