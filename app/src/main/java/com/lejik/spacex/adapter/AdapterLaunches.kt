package com.lejik.spacex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lejik.spacex.databinding.LaunchGeneralResItemsBinding
import com.lejik.spacex.model.Docs
import com.lejik.spacex.ui.viewholders.ViewHolderLaunches

class AdapterLaunches : PagingDataAdapter<Docs, ViewHolderLaunches>(DIFF_CALLBACK_LAUNCH) {
    companion object {
        private val DIFF_CALLBACK_LAUNCH = object : DiffUtil.ItemCallback<Docs>() {
            override fun areItemsTheSame(oldItem: Docs, newItem: Docs) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Docs, newItem: Docs) = oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolderLaunches, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderLaunches(
            LaunchGeneralResItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}