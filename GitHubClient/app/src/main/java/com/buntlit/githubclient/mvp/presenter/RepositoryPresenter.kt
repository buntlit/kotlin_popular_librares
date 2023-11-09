package com.buntlit.githubclient.mvp.presenter

import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.mvp.view.RepositoryView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoryPresenter(private val repository: GitHubRepository?, private val router: Router): MvpPresenter<RepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun getRepositoryName() = repository?.name
    fun getRepositoryId() = repository?.id
    fun getRepositoryForksCount() = repository?.forksCount

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}