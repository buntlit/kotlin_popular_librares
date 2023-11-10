package com.buntlit.githubclient.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGitHubUser(
   @PrimaryKey val id: String,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String
)