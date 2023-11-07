package com.buntlit.githubclient.mvp.model.repo.retrofit

import com.buntlit.githubclient.mvp.model.api.IDataSource
import com.buntlit.githubclient.mvp.model.repo.IGitHubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUsersRepo(private val api: IDataSource) : IGitHubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}