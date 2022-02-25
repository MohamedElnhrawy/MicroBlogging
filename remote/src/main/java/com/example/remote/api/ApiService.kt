package com.example.remote.api

import com.example.remote.model.AuthorResponseNetwork
import com.example.remote.model.PostResponseNetwork
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("authors")
    suspend fun getAuthors() : List<AuthorResponseNetwork>

    @GET("posts")
    suspend fun getAuthorPosts(
        @Query("authorId") authorId: Int
    ) : List<PostResponseNetwork>

}