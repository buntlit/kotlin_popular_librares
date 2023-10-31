package com.buntlit.githubclient.mvp.presenter

import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.mvp.view.UserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(private val user: GitHubUser?, private val router: Router) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun getLogin() = user?.login

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}