package com.buntlit.githubclient.mvp.model.repo.retrofit

import com.buntlit.githubclient.mvp.model.api.IDataSource
import com.buntlit.githubclient.mvp.model.cache.IGitHubRepositoriesCache
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.mvp.model.network.INetworkStatus
import com.buntlit.githubclient.mvp.model.repo.IGitHubRepositoriesRepo
import com.buntlit.githubclient.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGitHubRepositoriesCache
) : IGitHubRepositoriesRepo {
    override fun getRepositories(user: GitHubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->

            if (isOnline) {
                user.reposUrl.let { url ->
                    api.getRepositories(url!!).flatMap { repositories ->
                        cache.saveToCache(repositories, user)
                    }
                }

            } else {
                 cache.readFromCache(user)
            }
        }.subscribeOn(Schedulers.io())
}