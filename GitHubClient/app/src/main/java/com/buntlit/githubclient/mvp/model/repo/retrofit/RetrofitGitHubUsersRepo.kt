package com.buntlit.githubclient.mvp.model.repo.retrofit

import com.buntlit.githubclient.mvp.model.api.IDataSource
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.mvp.model.entity.room.Database
import com.buntlit.githubclient.mvp.model.entity.room.RoomGitHubUser
import com.buntlit.githubclient.mvp.model.network.INetworkStatus
import com.buntlit.githubclient.mvp.model.repo.IGitHubUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: Database
) : IGitHubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                Single.fromCallable {
                    val roomUsers = users.map { user ->
                        RoomGitHubUser(
                            user.id ?: "",
                            user.login ?: "",
                            user.avatarUrl ?: "",
                            user.reposUrl ?: ""
                        )
                    }
                    db.userDao.insert(roomUsers)
                    users
                }
            }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map {
                    GitHubUser(
                        it.id,
                        it.login,
                        it.avatarUrl,
                        it.reposUrl)
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}