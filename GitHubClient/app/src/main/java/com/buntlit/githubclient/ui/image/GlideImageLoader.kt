package com.buntlit.githubclient.ui.image

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.buntlit.githubclient.R
import com.buntlit.githubclient.mvp.model.cache.IGitHubImagesCache
import com.buntlit.githubclient.mvp.model.image.IImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.io.OutputStream

class GlideImageLoader(private val cache: IGitHubImagesCache) : IImageLoader<ImageView> {

    @SuppressLint("CheckResult")
    override fun loadImage(url: String, container: ImageView) {

        cache.readFromCache(url).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ image ->
                image.path?.let { loadInto(it, container) }
            }, {
                println("Error: ${it.message}")
                loadImageToStore(url, container)
//                loadInto(url, container)
            })
    }


    private fun loadInto(url: String, container: ImageView) {
        Glide.with(container)
            .asBitmap()
            .load(url)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(container)
    }

    private fun loadImageToStore(url: String, container: ImageView) {
        Glide.with(container)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                @SuppressLint("CheckResult")
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val imageName = "cached_$url.jpg"
                    val path =
                        writeImageToCache(imageName, resource, container.context)
                    cache.saveToCache(path, url)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({image->
                            image.path?.let { loadInto(it, container) }
                        }, {})
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    @SuppressLint("Recycle")
    private fun writeImageToCache(fileName: String, resource: Bitmap?, context: Context): String {
        var fOut: OutputStream?
        val imageUri: Uri?
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/Git")
        }
        val contentResolver = context.contentResolver
        contentResolver.also { resolver ->
            imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fOut = imageUri?.let { resolver.openOutputStream(it) }
        }
        fOut.use {
            if (it != null) {
                resource?.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
        }
        contentValues.clear()
        imageUri?.let { contentResolver.update(it, contentValues, null, null) }

        val cursor = imageUri?.let { contentResolver.query(it, null, null, null) }
        cursor?.moveToFirst()
        val index = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        return index?.let { cursor.getString(it) }.toString()
    }
}