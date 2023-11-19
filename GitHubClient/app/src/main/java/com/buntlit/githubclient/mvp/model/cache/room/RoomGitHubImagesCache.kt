package com.buntlit.githubclient.mvp.model.cache.room

import android.annotation.SuppressLint
import com.buntlit.githubclient.mvp.model.cache.IGitHubImagesCache
import com.buntlit.githubclient.mvp.model.entity.GitHubImage
import com.buntlit.githubclient.mvp.model.entity.room.Database
import com.buntlit.githubclient.mvp.model.entity.room.RoomGitHubImage
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGitHubImagesCache(
    private val db: Database
) : IGitHubImagesCache {


    @SuppressLint("CheckResult")
    override fun saveToCache(path: String, url: String) =
        Single.fromCallable {
            db.imageDao.insert(RoomGitHubImage(path, url))
            return@fromCallable GitHubImage(path, url)
        }.subscribeOn(Schedulers.io())


    @SuppressLint("CheckResult")
    override fun readFromCache(url: String) =
        Single.fromCallable {
            val roomImage = db.imageDao.findByImageUrl(url)
            return@fromCallable roomImage.let { GitHubImage(it.path, it.imageUrl) }
        }.subscribeOn(Schedulers.io())
}