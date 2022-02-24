package com.example.data.repository

import com.example.data.model.AuthorDTO


/**
 * Methods of Remote Data Source
 */
interface RemoteDataSource {
    suspend fun getAuthors() : List<AuthorDTO>


}