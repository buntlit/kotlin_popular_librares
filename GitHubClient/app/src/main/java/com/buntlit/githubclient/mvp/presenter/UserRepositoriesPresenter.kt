package com.buntlit.githubclient.mvp.presenter

import android.annotation.SuppressLint
import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.mvp.model.repo.IGitHubRepositoriesRepo
import com.buntlit.githubclient.mvp.presenter.list.IRepositoryListPresenter
import com.buntlit.githubclient.mvp.view.UserRepositoriesView
import com.buntlit.githubclient.mvp.view.list.RepositoryItemView
import com.buntlit.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserRepositoriesPresenter(
    private val mainThreadScheduler: Scheduler,
    private val userRepositoriesRepo: IGitHubRepositoriesRepo,
    private val router: Router
) :
    MvpPresenter<UserRepositoriesView>() {

    class RepositoriesListPresenter : IRepositoryListPresenter {

        val repositories = mutableListOf<GitHubRepository>()

        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setRepositoryName(it) }
        }

        override fun getCount() = repositories.size

    }

    val userRepositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userRepositoriesListPresenter.itemClickListener = { itemView ->
            router.navigateTo(
                Screens.UserScreens.repositoryFragment(
                    userRepositoriesListPresenter.repositories[itemView.pos]
                )
            )
        }
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        userRepositoriesRepo.getRepositories()
            .observeOn(mainThreadScheduler)
            .subscribe(
                { repositories ->
                    userRepositoriesListPresenter.repositories.addAll(repositories)
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}