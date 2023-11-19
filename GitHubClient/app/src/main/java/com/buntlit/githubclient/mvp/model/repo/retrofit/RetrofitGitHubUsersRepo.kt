package com.buntlit.githubclient.mvp.model.repo.retrofit

import com.buntlit.githubclient.mvp.model.api.IDataSource
import com.buntlit.githubclient.mvp.model.cache.IGithubUsersCache
import com.buntlit.githubclient.mvp.model.network.INetworkStatus
import com.buntlit.githubclient.mvp.model.repo.IGitHubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGithubUsersCache,
) : IGitHubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                cache.saveToCache(users)
            }
        } else {
            cache.readFromCache()
        }
    }.subscribeOn(Schedulers.io())
}