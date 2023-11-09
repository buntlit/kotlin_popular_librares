package com.buntlit.githubclient.mvp.model.repo

import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import io.reactivex.rxjava3.core.Single

interface IGitHubRepositoriesRepo {
    fun getRepositories() : Single<List<GitHubRepository>>
}