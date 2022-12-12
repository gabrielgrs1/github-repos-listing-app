package com.gabrielgrs2.listrepos.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gabrielgrs2.listrepos.R
import com.gabrielgrs2.listrepos.domain.model.Repository

class RepositoriesAdapter(
    private val context: Context
) : PagingDataAdapter<Repository, RepositoriesViewHolder>(REPOSITORY_COMPARATOR) {

    companion object {
        private val REPOSITORY_COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        return RepositoriesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_search_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        getItem(position)?.let { repository ->
            holder.bind(context, repository)
        }
    }
}