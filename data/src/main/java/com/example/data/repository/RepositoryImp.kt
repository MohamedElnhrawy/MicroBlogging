package com.example.data.repository

import com.example.common.Mapper
import com.example.common.Resource
import com.example.data.model.AuthorDTO
import com.example.domain.entity.AuthorEntity
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation class of [Repository]
 */
class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val authorMapper : Mapper<AuthorDTO, AuthorEntity>,
) : Repository {
    override suspend fun getAuthors(): Flow<Resource<List<AuthorEntity>>> {
        return flow {
            try {
                // Get data from RemoteDataSource
                val data = remoteDataSource.getAuthors()
                // Save to local or update if exist
                for (author in data){
                    val localId = localDataSource.getItem(author.id).id
                    if (localId==-1)
                        localDataSource.addItem(author)
                    else
                        localDataSource.updateItem(author)
                }
                // Emit data
                emit(Resource.Success(authorMapper.fromList(data)))
            } catch (ex : Exception) {
                // If remote request fails
                try {
                    // Get data from LocalDataSource
                    val local = localDataSource.getItems()
                    // Emit data
                    emit(Resource.Success(authorMapper.fromList(local)))
                } catch (ex1 : Exception) {
                    // Emit error
                    emit(Resource.Error(ex1))
                }
            }
        }
    }


}