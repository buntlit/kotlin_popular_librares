package com.buntlit.githubclient.mvp.model.cache

import com.buntlit.githubclient.mvp.model.entity.GitHubImage
import io.reactivex.rxjava3.core.Single

interface IGitHubImagesCache {
    fun saveToCache(path: String, url: String): Single<GitHubImage>
    fun readFromCache(url: String) : Single<GitHubImage>
}