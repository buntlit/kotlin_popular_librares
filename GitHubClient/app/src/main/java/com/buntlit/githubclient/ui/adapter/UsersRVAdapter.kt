package com.buntlit.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.buntlit.githubclient.databinding.ItemUserBinding
import com.buntlit.githubclient.mvp.model.image.IImageLoader
import com.buntlit.githubclient.mvp.presenter.list.IUserListPresenter
import com.buntlit.githubclient.mvp.view.list.UserItemView
import javax.inject.Inject

class UsersRVAdapter(
    private val presenter: IUserListPresenter
) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root), UserItemView {
        override fun setLogin(text: String) = with(binding) {
            tvLogin.text = text
        }

        override fun loadAvatar(url: String) = with(binding) {
            imageLoader.loadImage(url, ivAvatar)
        }

        override var pos = -1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )


    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.itemView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }
}