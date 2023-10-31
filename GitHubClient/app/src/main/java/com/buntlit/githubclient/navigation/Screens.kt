package com.buntlit.githubclient.navigation

import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.ui.fragments.UserFragment
import com.buntlit.githubclient.ui.fragments.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens {
    object UserScreens {
        fun usersFragment() = FragmentScreen { UsersFragment.newInstance() }
        fun userFragment(user: GitHubUser) = FragmentScreen { UserFragment.newInstance(user) }
    }
}