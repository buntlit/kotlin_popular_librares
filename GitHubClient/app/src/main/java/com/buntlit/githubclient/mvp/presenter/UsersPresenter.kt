package com.buntlit.githubclient.mvp.presenter

import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.mvp.model.entity.GitHubUsersRepo
import com.buntlit.githubclient.mvp.presenter.list.IUserListPresenter
import com.buntlit.githubclient.mvp.view.UsersView
import com.buntlit.githubclient.mvp.view.list.UserItemView
import com.buntlit.githubclient.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: GitHubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {

    class UserListPresenter : IUserListPresenter {

        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size

    }

    val usersListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.UserScreens.userFragment(
                usersListPresenter.users[itemView.pos]
            ))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}