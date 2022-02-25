package com.example.feature.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.base.BaseRecyclerAdapter
import com.example.feature.databinding.RowAuthorItemLayoutBinding
import com.example.feature.model.AuthorUiModel

/**
 * Adapter class for RecyclerView
 */
class AuthorsAdapter constructor(
    private val clickFunc : ((AuthorUiModel?) -> Unit)? = null
) : BaseRecyclerAdapter<AuthorUiModel, RowAuthorItemLayoutBinding, AuthorViewHolder>(AuthorItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val binding = RowAuthorItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return AuthorViewHolder(binding = binding, click = clickFunc)

    }

}

class AuthorItemDiffUtil : DiffUtil.ItemCallback<AuthorUiModel>() {
    override fun areItemsTheSame(oldItem: AuthorUiModel, newItem: AuthorUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AuthorUiModel, newItem: AuthorUiModel): Boolean {
        return oldItem == newItem
    }
}