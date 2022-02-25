package com.example.remote.mapper

import com.example.common.Mapper
import com.example.data.model.AuthorDTO
import com.example.data.model.PostDTO
import com.example.remote.model.AuthorResponseNetwork
import com.example.remote.model.PostResponseNetwork
import javax.inject.Inject


class PostNetworkDataMapper @Inject constructor() :
    Mapper<PostResponseNetwork, PostDTO> {

    override fun from(i: PostResponseNetwork?): PostDTO {
        return PostDTO(
            id = i?.id ?: 0,
            authorId = i?.authorId ?: -1,
            date = i?.date ?: "",
            title = i?.title ?: "",
            body = i?.body ?: "",
            imageUrl = i?.imageUrl ?: "",
        )
    }

    override fun to(o: PostDTO?): PostResponseNetwork {
        return PostResponseNetwork()
    }
}