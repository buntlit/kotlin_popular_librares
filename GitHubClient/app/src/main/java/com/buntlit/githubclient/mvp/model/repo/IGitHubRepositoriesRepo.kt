package com.buntlit.githubclient.mvp.model.repo

import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IGitHubRepositoriesRepo {
    fun getRepositories(user: GitHubUser) : Single<List<GitHubRepository>>
}