package com.buntlit.githubclient.mvp.presenter

import android.annotation.SuppressLint
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.mvp.model.repo.IGitHubUsersRepo
import com.buntlit.githubclient.mvp.presenter.list.IUserListPresenter
import com.buntlit.githubclient.mvp.view.UsersView
import com.buntlit.githubclient.mvp.view.list.UserItemView
import com.buntlit.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UsersPresenter(
    private val mainThreadScheduler: Scheduler,
    private val usersRepo: IGitHubUsersRepo,
    private val router: Router
) :
    MvpPresenter<UsersView>() {

    class UserListPresenter : IUserListPresenter {

        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }

        override fun getCount() = users.size

    }

    val usersListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(
                Screens.UserScreens.userRepositoriesFragment(
                    usersListPresenter.users[itemView.pos]
                )
            )
        }
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        usersRepo.getUsers().observeOn(mainThreadScheduler).subscribe({ user ->
            usersListPresenter.users.addAll(user)
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