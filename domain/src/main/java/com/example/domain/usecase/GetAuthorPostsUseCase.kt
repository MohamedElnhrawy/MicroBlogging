package com.example.domain.usecase

import com.example.common.Resource
import com.example.domain.entity.AuthorEntity
import com.example.domain.entity.PostEntity
import com.example.domain.qualifiers.IoDispatcher
import com.example.domain.repository.Repository
import com.example.domain.usecase.core.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Use Case class for get Author
 */
class GetAuthorPostsUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<PostEntity, Int>() {

    override suspend fun buildRequest(params: Int?): Flow<Resource<List<PostEntity>>> {
        if (params == null) {
            return flow {
                emit(Resource.Error(Exception("id can not be null")))
            }.flowOn(dispatcher)
        }
        return  repository.getAuthorPosts(params).flowOn(dispatcher)
    }


}