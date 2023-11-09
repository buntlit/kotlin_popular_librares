package com.buntlit.githubclient.navigation

import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.ui.fragments.RepositoryFragment
import com.buntlit.githubclient.ui.fragments.UserRepositoriesRepositoriesFragment
import com.buntlit.githubclient.ui.fragments.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens {
    object UserScreens {
        fun usersFragment() = FragmentScreen { UsersFragment.newInstance() }
        fun userRepositoriesFragment(user: String) = FragmentScreen { UserRepositoriesRepositoriesFragment.newInstance(user) }
        fun repositoryFragment(repository: GitHubRepository) = FragmentScreen{RepositoryFragment.newInstance(repository)}
    }
}