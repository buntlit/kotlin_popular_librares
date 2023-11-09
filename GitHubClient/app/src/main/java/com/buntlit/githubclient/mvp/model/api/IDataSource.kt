package com.buntlit.githubclient.mvp.model.api

import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GitHubUser>>

    @GET
    fun getRepositories(@Url string : String): Single<List<GitHubRepository>>
}