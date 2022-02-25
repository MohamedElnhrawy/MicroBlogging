package com.example.feature.ui.main

import com.bumptech.glide.Glide
import com.example.base.BaseViewHolder
import com.example.common.loadImagesWithGlideExt
import com.example.feature.databinding.RowAuthorItemLayoutBinding
import com.example.feature.model.AuthorUiModel

/**
 * ViewHolder class for author
 */
class AuthorViewHolder constructor(
    private val binding : RowAuthorItemLayoutBinding,
    private val click : ((AuthorUiModel?) -> Unit)? = null
) : BaseViewHolder<AuthorUiModel, RowAuthorItemLayoutBinding>(binding) {


    init {
        binding.container.setOnClickListener {
            click?.invoke(getRowItem())
        }
    }

    override fun bind() {


        getRowItem()?.let {
            binding.author = it
            binding.executePendingBindings()

        }
    }
}