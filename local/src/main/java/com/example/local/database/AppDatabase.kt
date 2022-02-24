package com.example.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.model.AuthorLocalModel

@Database(entities = [AuthorLocalModel::class], version = 1, exportSchema = false) // We need migration if increase version
abstract class AppDatabase : RoomDatabase() {
    abstract fun authorDao() : AuthorDAO
}