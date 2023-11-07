package com.buntlit.githubclient.mvp.model.entity

import io.reactivex.rxjava3.core.Observable


class GitHubUsersRepo {
    private val repositories = listOf(
        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5")
    )

    fun getUsers(): Observable<GitHubUser> {
        return Observable.fromIterable(repositories)
    }
}