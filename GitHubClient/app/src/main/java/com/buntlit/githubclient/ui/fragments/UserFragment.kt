package com.buntlit.githubclient.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buntlit.githubclient.App
import com.buntlit.githubclient.databinding.FragmentUserBinding
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.mvp.presenter.UserPresenter
import com.buntlit.githubclient.mvp.view.UserView
import com.buntlit.githubclient.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var binding: FragmentUserBinding? = null
    private val user: GitHubUser? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(USER_KEY, GitHubUser::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(USER_KEY)
        }
    }
    private val presenter by moxyPresenter { UserPresenter(user, App.INSTANCE.router) }

    companion object {

        private const val USER_KEY = "USER"

        fun newInstance(user: GitHubUser? = null): UserFragment {
            val fragment = UserFragment()
            val arguments = Bundle()
            arguments.putParcelable(USER_KEY, user)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater)
        return binding?.root
    }

    override fun init() {
        binding?.login?.text = presenter.getLogin()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed() = presenter.backPressed()
}