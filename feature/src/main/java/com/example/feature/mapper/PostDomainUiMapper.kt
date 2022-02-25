package com.example.feature.mapper

import com.example.common.Mapper
import com.example.domain.entity.AuthorEntity
import com.example.domain.entity.PostEntity
import com.example.feature.model.AuthorUiModel
import com.example.feature.model.PostUiModel
import javax.inject.Inject


class PostDomainUiMapper @Inject constructor() : Mapper<PostEntity, PostUiModel> {


    override fun from(i: PostEntity?): PostUiModel {
        return  PostUiModel(
            id = i?.id?:0,
            authorId = i?.authorId?:-1,
            date = i?.date ?: "",
            title = i?.title ?: "",
            body = i?.body ?: "",
            imageUrl = i?.imageUrl ?: ""
        )
    }

    override fun to(o: PostUiModel?): PostEntity {
        return  PostEntity(
            id = o?.id?:0,
            authorId = o?.authorId?:-1,
            date = o?.date ?: "",
            title = o?.title ?: "",
            body = o?.body ?: "",
            imageUrl = o?.imageUrl ?: ""
        )
    }
}