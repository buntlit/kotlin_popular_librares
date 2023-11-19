package com.buntlit.githubclient.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GitHubImage(
    val path: String?,
    val imageUrl: String?
) : Parcelable