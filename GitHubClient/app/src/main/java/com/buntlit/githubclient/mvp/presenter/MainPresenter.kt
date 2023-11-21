package com.buntlit.githubclient.mvp.presenter

import com.buntlit.githubclient.mvp.view.MainView
import com.buntlit.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(Screens.UserScreens.usersFragment())
    }

    fun backClicked(){
        router.exit()
    }
}