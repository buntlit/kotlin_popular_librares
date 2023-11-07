package com.buntlit.githubclient.ui.image

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.buntlit.githubclient.R
import com.buntlit.githubclient.mvp.model.image.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container)
            .load(Uri.parse(url)).placeholder(R.mipmap.ic_launcher_round)
            .into(container)
    }
}