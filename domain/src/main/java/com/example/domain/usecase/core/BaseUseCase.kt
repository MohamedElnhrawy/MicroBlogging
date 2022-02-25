package com.example.domain.usecase.core

import androidx.annotation.Nullable
import com.example.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Base Use Case class
 */
abstract class BaseUseCase<Model, Params> {

    abstract suspend fun buildRequest(params: Params?): Flow<Resource<List<Model>>>
//
//    suspend fun execute(): Flow<Resource<List<Model>>> {
//        return try {
//            buildRequest()
//        } catch (exception: Exception) {
//            flow { emit(Resource.Error(exception)) }
//        }
//    }

//    abstract suspend fun buildRequest(@Nullable params: Params?): Flow<Resource<Model>>

    suspend fun execute(params: Params?): Flow<Resource<List<Model>>> {
        return try {
            buildRequest(params)
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception)) }
        }
    }
}