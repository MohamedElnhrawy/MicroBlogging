package com.example.domain.repository

import com.example.common.Resource
import com.example.domain.entity.AuthorEntity
import kotlinx.coroutines.flow.Flow

/**
 * Methods of Repository
 */
interface Repository {
    suspend fun getAuthors() : Flow<Resource<List<AuthorEntity>>>

}