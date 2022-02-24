package com.example.local.source

import com.example.common.Mapper
import com.example.data.model.AuthorDTO
import com.example.data.repository.LocalDataSource
import com.example.local.database.AuthorDAO
import com.example.local.model.AuthorLocalModel
import javax.inject.Inject

/**
 * Implementation of [LocalDataSource] Source
 */
class LocalDataSourceImp @Inject constructor(
    private val authorDAO: AuthorDAO,
    private val authorMapper : Mapper<AuthorLocalModel, AuthorDTO>
) : LocalDataSource {
    override suspend fun addItem(author: AuthorDTO): Long {
        val authorLocalModel = authorMapper.to(author)
        return authorDAO.addAuthorItem(author = authorLocalModel)
    }


    override suspend fun getItem(id: Int): AuthorDTO {
        val authorLocalModel = authorDAO.getAuthorItem(id = id)
        return authorMapper.from(authorLocalModel)
    }

    override suspend fun addItems(authors: List<AuthorDTO>): List<Long> {
        val authorLocalList = authorMapper.toList(authors)
        return authorDAO.addAuthorItems(author = authorLocalList)
    }

    override suspend fun getItems(): List<AuthorDTO> {
        val authorLocalList = authorDAO.getAuthorItems()
        return authorMapper.fromList(authorLocalList)
    }

    override suspend fun updateItem(author: AuthorDTO): Int {
        val authorLocalModel = authorMapper.to(author)
        return authorDAO.updateAuthorItem(author = authorLocalModel)
    }

    override suspend fun deleteItemById(id: Int): Int {
        return authorDAO.deleteAuthorItemById(id = id)
    }

    override suspend fun deleteItem(author: AuthorDTO): Int {
        val authorLocalModel = authorMapper.to(author)
        return authorDAO.deleteAuthorItem(author = authorLocalModel)    }

    override suspend fun clearCachedItems(): Int {
        return authorDAO.clearCachedAuthorItems()
    }


}