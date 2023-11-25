package com.buntlit.githubclient.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["avatarUrl"],
        childColumns = ["imageUrl"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [androidx.room.Index(value = ["imageUrl"], unique = true)]
)
class RoomGitHubImage(
    @PrimaryKey val path: String,
    val imageUrl: String

)