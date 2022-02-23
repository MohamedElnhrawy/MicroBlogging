package com.example.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = false) // We need migration if increase version
abstract class AppDatabase : RoomDatabase() {

}