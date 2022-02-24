package com.example.domain.usecase

import com.example.common.Resource
import com.example.domain.entity.AuthorEntity
import com.example.domain.qualifiers.IoDispatcher
import com.example.domain.repository.Repository
import com.example.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use Case class for get Author
 */
class GetAuthorUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<AuthorEntity, String>() {

    override suspend fun buildRequest(): Flow<Resource<List<AuthorEntity>>> {
        return  repository.getAuthors().flowOn(dispatcher)
    }
}