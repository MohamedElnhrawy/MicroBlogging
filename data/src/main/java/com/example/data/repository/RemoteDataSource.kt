package com.example.data.repository

import com.example.data.model.AuthorDTO
import com.example.data.model.PostDTO


/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {
    suspend fun getAuthors() : List<AuthorDTO>
    suspend fun getAuthorPosts(authorID:Int) : List<PostDTO>


}