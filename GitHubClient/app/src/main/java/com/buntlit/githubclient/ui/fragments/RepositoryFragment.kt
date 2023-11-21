package com.buntlit.githubclient.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buntlit.githubclient.App
import com.buntlit.githubclient.databinding.FragmentRepositoryBinding
import com.buntlit.githubclient.mvp.model.entity.GitHubRepository
import com.buntlit.githubclient.mvp.presenter.RepositoryPresenter
import com.buntlit.githubclient.mvp.view.RepositoryView
import com.buntlit.githubclient.ui.BackButtonListener
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    @Inject
    lateinit var router: Router

    private var binding: FragmentRepositoryBinding? = null
    private val repository: GitHubRepository? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(REPOSITORY_KEY, GitHubRepository::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(REPOSITORY_KEY)
        }
    }
    private val presenter by moxyPresenter { RepositoryPresenter(repository, router) }


    companion object {
        private const val REPOSITORY_KEY = "REPO"

        fun newInstance(repository: GitHubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_KEY, repository)
            }

            App.INSTANCE.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun init() {
    }

    override fun setId(text: String) {
        binding?.repositoryId?.text = text
    }

    override fun setName(text: String) {
        binding?.repositoryName?.text = text
    }

    override fun setForks(text: String) {
        binding?.repositoryForksCount?.text = text
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}