package com.example.data.repository

import com.example.data.model.AuthorDTO


/**
 * Methods of Local Data Source
 */
interface LocalDataSource {
    suspend fun addItem(author : AuthorDTO) : Long

    suspend fun getItem(id: Int): AuthorDTO

    suspend fun addItems(authors: List<AuthorDTO>) : List<Long>

    suspend fun getItems(): List<AuthorDTO>

    suspend fun updateItem(author: AuthorDTO): Int

    suspend fun deleteItemById(id : Int) : Int

    suspend fun deleteItem(author : AuthorDTO) : Int

    suspend fun clearCachedItems(): Int
}