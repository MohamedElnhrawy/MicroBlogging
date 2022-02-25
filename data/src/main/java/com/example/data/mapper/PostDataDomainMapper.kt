package com.example.data.mapper

import com.example.common.Mapper
import com.example.data.model.AuthorDTO
import com.example.data.model.PostDTO
import com.example.domain.entity.AuthorEntity
import com.example.domain.entity.PostEntity
import javax.inject.Inject


class PostDataDomainMapper @Inject constructor() :
    Mapper<PostDTO, PostEntity> {

    override fun from(i: PostDTO?): PostEntity {
        return PostEntity(
            id = i?.id ?: -1,
            authorId = i?.authorId ?: -1,
            date = i?.date ?: "",
            title = i?.title ?: "",
            body = i?.body ?: "",
            imageUrl = i?.imageUrl ?: "",

        )
    }


    override fun to(o: PostEntity?): PostDTO {
        return PostDTO(
            id = o?.id ?: -1,
            authorId = o?.authorId ?: -1,
            date = o?.date ?: "",
            title = o?.title ?: "",
            body = o?.body ?: "",
            imageUrl = o?.imageUrl ?: "",

        )
    }
}