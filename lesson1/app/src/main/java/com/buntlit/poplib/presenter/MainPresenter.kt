package com.buntlit.poplib.presenter

import com.buntlit.poplib.model.CounterModel
import com.buntlit.poplib.view.MainView

class MainPresenter(private val view: MainView) {
    private val model = CounterModel()

    fun counterClick(index: Int) {
        val nextValue = model.next(index)
        view.setButtonText(index, nextValue.toString())
    }
}