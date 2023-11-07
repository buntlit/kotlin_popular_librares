package com.buntlit.githubclient.mvp.model.repo

import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IGitHubUsersRepo {
    fun getUsers() : Single<List<GitHubUser>>
}