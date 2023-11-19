package com.buntlit.githubclient.mvp.model.entity.room.dao

import androidx.room.*
import com.buntlit.githubclient.mvp.model.entity.room.RoomGitHubImage

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: RoomGitHubImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg image: RoomGitHubImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<RoomGitHubImage>)

    @Update
    fun update(image: RoomGitHubImage)

    @Update
    fun update(vararg image: RoomGitHubImage)

    @Update
    fun update(images: List<RoomGitHubImage>)

    @Delete
    fun delete(image: RoomGitHubImage)

    @Delete
    fun delete(vararg image: RoomGitHubImage)

    @Delete
    fun delete(images: List<RoomGitHubImage>)

    @Query("SELECT * FROM RoomGitHubImage")
    fun getAll(): List<RoomGitHubImage>

    @Query("SELECT * FROM RoomGitHubImage WHERE imageUrl = :imageUrl LIMIT 1")
    fun findByImageUrl(imageUrl: String): RoomGitHubImage
}