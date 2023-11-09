package com.buntlit.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GitHubRepository(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val forksCount: Int? = null
) : Parcelable