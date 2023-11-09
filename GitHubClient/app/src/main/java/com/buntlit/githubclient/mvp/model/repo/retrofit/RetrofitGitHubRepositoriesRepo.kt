package com.buntlit.githubclient.mvp.model.repo.retrofit

import com.buntlit.githubclient.mvp.model.api.IDataSource
import com.buntlit.githubclient.mvp.model.repo.IGitHubRepositoriesRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubRepositoriesRepo(private val api: IDataSource, private val url: String) : IGitHubRepositoriesRepo {
    override fun getRepositories() = api.getRepositories(url).subscribeOn(Schedulers.io())
}