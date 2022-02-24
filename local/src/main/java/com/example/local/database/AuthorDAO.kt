package com.example.local.database

import androidx.room.*
import com.example.local.model.AuthorLocalModel

@Dao
interface AuthorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAuthorItem(author : AuthorLocalModel) : Long

    @Query("SELECT * FROM author WHERE id = :id")
    suspend fun getAuthorItem(id: Int): AuthorLocalModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAuthorItems(author: List<AuthorLocalModel>) : List<Long>

    @Query("SELECT * FROM author")
    suspend fun getAuthorItems(): List<AuthorLocalModel>

    @Update
    suspend fun updateAuthorItem(author: AuthorLocalModel): Int

    @Query("DELETE FROM author WHERE id = :id")
    suspend fun deleteAuthorItemById(id : Int) : Int

    @Delete
    suspend fun deleteAuthorItem(author : AuthorLocalModel) : Int

    @Query("DELETE FROM author")
    suspend fun clearCachedAuthorItems(): Int
}