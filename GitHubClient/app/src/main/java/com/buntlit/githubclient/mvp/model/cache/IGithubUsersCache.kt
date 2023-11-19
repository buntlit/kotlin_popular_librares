package com.buntlit.githubclient.mvp.model.cache

import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun saveToCache(users: List<GitHubUser>): Single<List<GitHubUser>>
    fun readFromCache() : Single<List<GitHubUser>>
}