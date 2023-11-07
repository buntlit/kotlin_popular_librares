package com.buntlit.picturetypeconvertor.model

import android.os.Parcelable
import io.reactivex.rxjava3.core.Single
import kotlinx.parcelize.Parcelize

@Parcelize
class Model(var pictureWay: String = "") : Parcelable {
    fun subscribeModel(): Single<Model> {
        return Single.just(this)
    }
}