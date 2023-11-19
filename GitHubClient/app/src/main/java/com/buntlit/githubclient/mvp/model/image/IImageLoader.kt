package com.buntlit.githubclient.mvp.model.image

interface IImageLoader<T> {
    fun loadImage(url: String, container: T)
}