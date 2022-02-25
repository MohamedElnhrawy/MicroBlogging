package com.example.feature.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.base.BaseRecyclerAdapter
import com.example.feature.databinding.RowPostItemLayoutBinding
import com.example.feature.model.PostUiModel

/**
 * Adapter class for RecyclerView
 */
class PostsAdapter: BaseRecyclerAdapter<PostUiModel, RowPostItemLayoutBinding, PostViewHolder>(PostItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = RowPostItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return PostViewHolder(binding = binding)

    }

}

class PostItemDiffUtil : DiffUtil.ItemCallback<PostUiModel>() {
    override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem == newItem
    }
}