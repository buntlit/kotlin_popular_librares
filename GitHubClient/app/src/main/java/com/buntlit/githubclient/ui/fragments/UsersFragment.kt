package com.buntlit.githubclient.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.buntlit.githubclient.ApiHolder
import com.buntlit.githubclient.App
import com.buntlit.githubclient.databinding.FragmentUsersBinding
import com.buntlit.githubclient.mvp.model.repo.retrofit.RetrofitGitHubUsersRepo
import com.buntlit.githubclient.mvp.presenter.UsersPresenter
import com.buntlit.githubclient.mvp.view.UsersView
import com.buntlit.githubclient.ui.BackButtonListener
import com.buntlit.githubclient.ui.adapter.UsersRVAdapter
import com.buntlit.githubclient.ui.image.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var binding: FragmentUsersBinding? = null
    private lateinit var adapter: UsersRVAdapter
    private val presenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGitHubUsersRepo(ApiHolder().api),
            App.INSTANCE.router
        )
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater)
        return binding?.root
    }

    override fun init() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding?.rvUsers?.adapter = adapter
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