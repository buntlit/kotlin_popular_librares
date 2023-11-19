package com.buntlit.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GitHubUser(
    @Expose val id: String?,
    @Expose val login: String?,
    @Expose val avatarUrl: String?,
    @Expose val reposUrl: String?
) : Parcelable