package com.buntlit.githubclient.mvp.di.modules

import com.buntlit.githubclient.mvp.model.api.IDataSource
import com.buntlit.githubclient.mvp.model.cache.IGitHubRepositoriesCache
import com.buntlit.githubclient.mvp.model.cache.IGithubUsersCache
import com.buntlit.githubclient.mvp.model.network.INetworkStatus
import com.buntlit.githubclient.mvp.model.repo.retrofit.RetrofitGitHubRepositoriesRepo
import com.buntlit.githubclient.mvp.model.repo.retrofit.RetrofitGitHubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache
    ) = RetrofitGitHubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGitHubRepositoriesCache
    ) = RetrofitGitHubRepositoriesRepo(api, networkStatus, cache)
}