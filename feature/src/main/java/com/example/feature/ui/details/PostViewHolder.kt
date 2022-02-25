package com.example.feature.ui.details

import com.example.base.BaseViewHolder
import com.example.feature.databinding.RowPostItemLayoutBinding
import com.example.feature.model.PostUiModel

/**
 * ViewHolder class for post
 */
class PostViewHolder constructor(
    private val binding : RowPostItemLayoutBinding,
) : BaseViewHolder<PostUiModel, RowPostItemLayoutBinding>(binding) {


    init {

    }

    override fun bind() {
        getRowItem()?.let {
            binding.post = it
            binding.executePendingBindings()

        }
    }
}