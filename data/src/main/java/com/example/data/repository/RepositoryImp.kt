package com.example.data.repository

import com.example.common.Mapper
import com.example.domain.repository.Repository
import javax.inject.Inject

/**
 * Implementation class of [Repository]
 */
class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapper : Mapper<Any, Any>,
) : Repository {


}