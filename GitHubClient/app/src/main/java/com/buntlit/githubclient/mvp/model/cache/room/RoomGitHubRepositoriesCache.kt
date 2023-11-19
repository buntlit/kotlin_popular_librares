package com.buntlit.githubclient.mvp.model.cache.room

import android.annotation.SuppressLint
import com.buntlit.githubclient.mvp.model.cache.IGitHubRepositoriesCache
import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.mvp.model.entity.room.Database
import com.buntlit.githubclient.mvp.model.entity.room.RoomGitHubRepository
import io.reactivex.rxjava3.core.Single

class RoomGitHubRepositoriesCache(
    private val db: Database
) : IGitHubRepositoriesCache {

    override fun saveToCache(repositories: List<GitHubRepository>, user: GitHubUser) =
        Single.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                ?: throw Exception("No such user in cache")
            val roomRepositories = repositories.map {
                RoomGitHubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    it.forksCount ?: 0,
                    roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepositories)
            return@fromCallable repositories
        }

    @SuppressLint("CheckResult")
    override fun readFromCache(user: GitHubUser) =
        Single.fromCallable {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                ?: throw Exception("No such user in cache")
            return@fromCallable db.repositoryDao.findByUserId(roomUser.id)
                .map { GitHubRepository(it.id, it.name, it.forksCount) }
        }

}