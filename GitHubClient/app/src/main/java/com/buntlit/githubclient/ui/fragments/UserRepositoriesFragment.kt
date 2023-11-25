package com.buntlit.githubclient.ui.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.buntlit.githubclient.App
import com.buntlit.githubclient.databinding.FragmentUserRepositoriesBinding
import com.buntlit.githubclient.mvp.model.entity.GitHubUser
import com.buntlit.githubclient.mvp.presenter.UserRepositoriesPresenter
import com.buntlit.githubclient.mvp.view.UserRepositoriesView
import com.buntlit.githubclient.ui.BackButtonListener
import com.buntlit.githubclient.ui.adapter.RepositoriesRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserRepositoriesFragment : MvpAppCompatFragment(), UserRepositoriesView,
    BackButtonListener {

    private var binding: FragmentUserRepositoriesBinding? = null
    private lateinit var adapter: RepositoriesRVAdapter
    private val user: GitHubUser by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(USER_KEY,GitHubUser::class.java) as GitHubUser
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(USER_KEY)!!
        }
    }
    private val presenter by moxyPresenter {
        UserRepositoriesPresenter(
            user
        ).apply {
            App.INSTANCE.appComponent.inject(this)
        }
    }

    companion object {

        private const val USER_KEY = "USER"

        fun newInstance(user: GitHubUser) = UserRepositoriesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_KEY, user)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRepositoriesBinding.inflate(inflater)
        return binding?.root
    }

    override fun init() {
        binding?.rvRepositories?.layoutManager = LinearLayoutManager(requireContext())
        adapter = RepositoriesRVAdapter(presenter.userRepositoriesListPresenter)
        binding?.rvRepositories?.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed() = presenter.backPressed()
}