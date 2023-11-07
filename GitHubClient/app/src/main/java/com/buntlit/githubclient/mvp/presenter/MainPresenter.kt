package com.buntlit.githubclient.mvp.presenter

import com.buntlit.githubclient.mvp.view.MainView
import com.buntlit.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(Screens.UserScreens.usersFragment())
    }

    fun backClicked(){
        router.exit()
    }
}