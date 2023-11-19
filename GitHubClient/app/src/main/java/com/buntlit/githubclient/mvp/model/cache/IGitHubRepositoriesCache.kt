package com.buntlit.githubclient.mvp.model.cache

import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IGitHubRepositoriesCache {
    fun saveToCache(repositories: List<GitHubRepository>, user: GitHubUser): Single<List<GitHubRepository>>
    fun readFromCache(user: GitHubUser) : Single<List<GitHubRepository>>
}