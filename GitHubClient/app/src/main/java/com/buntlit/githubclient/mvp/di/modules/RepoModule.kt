package com.buntlit.githubclient.mvp.di.modules

import android.widget.ImageView
import com.buntlit.githubclient.mvp.model.api.IDataSource
import com.buntlit.githubclient.mvp.model.cache.IGitHubImagesCache
import com.buntlit.githubclient.mvp.model.cache.IGitHubRepositoriesCache
import com.buntlit.githubclient.mvp.model.cache.IGithubUsersCache
import com.buntlit.githubclient.mvp.model.image.IImageLoader
import com.buntlit.githubclient.mvp.model.network.INetworkStatus
import com.buntlit.githubclient.mvp.model.repo.IGitHubRepositoriesRepo
import com.buntlit.githubclient.mvp.model.repo.IGitHubUsersRepo
import com.buntlit.githubclient.mvp.model.repo.retrofit.RetrofitGitHubRepositoriesRepo
import com.buntlit.githubclient.mvp.model.repo.retrofit.RetrofitGitHubUsersRepo
import com.buntlit.githubclient.ui.image.GlideImageLoader
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
    ) : IGitHubUsersRepo = RetrofitGitHubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGitHubRepositoriesCache
    ): IGitHubRepositoriesRepo = RetrofitGitHubRepositoriesRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun imageLoader(
        cache: IGitHubImagesCache
    ): IImageLoader<ImageView> = GlideImageLoader(cache)
}