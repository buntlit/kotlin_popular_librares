package com.buntlit.githubclient.navigation

import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.ui.fragments.RepositoryFragment
import com.buntlit.githubclient.ui.fragments.UserRepositoriesFragment
import com.buntlit.githubclient.ui.fragments.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens {
    object UserScreens {
        fun usersFragment() = FragmentScreen { UsersFragment.newInstance() }
        fun userRepositoriesFragment(user: GitHubUser) = FragmentScreen { UserRepositoriesFragment.newInstance(user) }
        fun repositoryFragment(repository: GitHubRepository) = FragmentScreen{RepositoryFragment.newInstance(repository)}
    }
}