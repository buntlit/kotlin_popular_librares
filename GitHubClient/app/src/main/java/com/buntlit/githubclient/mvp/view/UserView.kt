package com.buntlit.githubclient.mvp.view

import com.buntlit.githubclient.mvp.view.list.UserItemView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView: MvpView {
    fun init()
}