package com.buntlit.picturetypeconvertor.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.buntlit.picturetypeconvertor.model.Model
import com.buntlit.picturetypeconvertor.view.MainFragmentView
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class MainFragmentPresenter : MvpPresenter<MainFragmentView>() {
    private var model = Model()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    @SuppressLint("CheckResult")
    fun setPathway(pathway: String){
        model.subscribeModel().subscribeOn(Schedulers.io()).subscribe({
            it.pictureWay = pathway
        }) {
            Log.d("SAUSAGES", "setPathway: $it")
        }
    }
    @SuppressLint("CheckResult")
    fun getPathway(): String{
        var pathway = ""
        model.subscribeModel().subscribe({
            pathway = it.pictureWay
        }){
            Log.d("SAUSAGES", "getPathway: $it")
        }
        return pathway
    }
}