package com.buntlit.githubclient.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.buntlit.githubclient.App
import com.buntlit.githubclient.databinding.FragmentUsersBinding
import com.buntlit.githubclient.mvp.presenter.UsersPresenter
import com.buntlit.githubclient.mvp.view.UsersView
import com.buntlit.githubclient.ui.BackButtonListener
import com.buntlit.githubclient.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var binding: FragmentUsersBinding? = null
    private lateinit var adapter: UsersRVAdapter
    private val presenter by moxyPresenter {
        UsersPresenter().apply {
            App.INSTANCE.appComponent.inject(this)
        }
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
        adapter = UsersRVAdapter(presenter.usersListPresenter).apply {
            App.INSTANCE.appComponent.inject(this)
        }
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