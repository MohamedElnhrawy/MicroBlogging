package com.example.remote.source

import com.example.common.Mapper
import com.example.data.model.AuthorDTO
import com.example.data.model.PostDTO
import com.example.data.repository.RemoteDataSource
import com.example.remote.api.ApiService
import com.example.remote.model.AuthorResponseNetwork
import com.example.remote.model.PostResponseNetwork
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(
    private val apiService : ApiService,
    private val authorMapper : Mapper<AuthorResponseNetwork, AuthorDTO>,
    private val postMapper : Mapper<PostResponseNetwork, PostDTO>,
    ) : RemoteDataSource {
    override suspend fun getAuthors(): List<AuthorDTO> {
        val networkData = apiService.getAuthors()
        return authorMapper.fromList(networkData)
    }

    override suspend fun getAuthorPosts(AuthorID:Int): List<PostDTO> {
        val networkData = apiService.getAuthorPosts(AuthorID)
        return postMapper.fromList(networkData)
    }


}