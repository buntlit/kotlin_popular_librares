package com.buntlit.githubclient.mvp.model.entity.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUser::class,
        parentColumns = ["avatarUrl"],
        childColumns = ["imageUrl"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomGitHubImage(
    @PrimaryKey val path: String,
    @ColumnInfo(index = true) val imageUrl: String

)