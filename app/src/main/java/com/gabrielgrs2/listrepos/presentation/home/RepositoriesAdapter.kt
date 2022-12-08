package com.gabrielgrs2.listrepos.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielgrs2.listrepos.R
import com.gabrielgrs2.listrepos.databinding.ItemSearchListBinding
import com.gabrielgrs2.listrepos.domain.model.Repository
import com.squareup.picasso.Picasso

class RepositoriesAdapter(
    var repositories: List<Repository>,
    private val context: Context
) : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        return RepositoriesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_search_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.bindView(context, repositories[position])
    }

    override fun getItemCount(): Int = repositories.size

    class RepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding: ItemSearchListBinding

        init {
            binding = ItemSearchListBinding.bind(itemView)
        }

        fun bindView(
            context: Context,
            repository: Repository
        ) {

            binding.repositoryNameTv.text = repository.name
            binding.repositoryStarsTv.text = repository.starGazersCount.toString()
            binding.repositoryForksCountTv.text = repository.forksCount.toString()
            binding.userAuthorTv.text = repository.owner.login

            Picasso
                .with(context)
                .load(repository.owner.avatarUrl)
                .placeholder(R.drawable.progress_animation)
                .into(binding.userAvatarIv)
        }
    }
}