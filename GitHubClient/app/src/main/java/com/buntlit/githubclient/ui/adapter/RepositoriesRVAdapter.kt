package com.buntlit.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buntlit.githubclient.databinding.ItemRepositoriesBinding
import com.buntlit.githubclient.mvp.presenter.list.IRepositoryListPresenter
import com.buntlit.githubclient.mvp.view.list.RepositoryItemView

class RepositoriesRVAdapter(private val presenter: IRepositoryListPresenter) :
    RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRepositoriesBinding) :
        RecyclerView.ViewHolder(binding.root), RepositoryItemView {
        override fun setRepositoryName(text: String) = with(binding) {
            tvRepository.text = text
        }

        override var pos = -1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemRepositoriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.itemView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }
}