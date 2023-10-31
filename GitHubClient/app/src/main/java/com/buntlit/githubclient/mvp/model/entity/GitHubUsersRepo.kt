package com.buntlit.githubclient.mvp.model.entity

class GitHubUsersRepo {
    private val repositories  = listOf(
        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5")
    )

    fun getUsers(): List<GitHubUser> {
        return repositories
    }
}