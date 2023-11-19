package com.buntlit.githubclient.mvp.presenter

import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.mvp.view.RepositoryView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoryPresenter(private val repository: GitHubRepository?, private val router: Router): MvpPresenter<RepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        repository?.let { viewState.setId(it.id.toString()) }
        repository?.let { viewState.setName(it.name.toString()) }
        repository?.let { viewState.setForks(it.forksCount.toString()) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}