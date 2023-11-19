package com.buntlit.githubclient.mvp.model.cache.room

import com.buntlit.githubclient.mvp.model.cache.IGithubUsersCache
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.mvp.model.entity.room.Database
import com.buntlit.githubclient.mvp.model.entity.room.RoomGitHubUser
import io.reactivex.rxjava3.core.Single

class RoomGitHubUsersCache(
    private val db: Database
) : IGithubUsersCache {
    override fun saveToCache(users: List<GitHubUser>) =
        Single.fromCallable {
            val roomUsers = users.map {
                RoomGitHubUser(
                    it.id ?: "",
                    it.login ?: "",
                    it.avatarUrl ?: "",
                    it.reposUrl ?: ""
                )
            }
            db.userDao.insert(roomUsers)
            return@fromCallable users
        }

    override fun readFromCache() =
        Single.fromCallable {
            return@fromCallable db.userDao.getAll().map {
                GitHubUser(
                    it.id,
                    it.login,
                    it.avatarUrl,
                    it.reposUrl
                )
            }
        }
}