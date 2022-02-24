package com.example.local.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "author")
data class AuthorLocalModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val avatarUrl: String,
//    @Ignore
//    val address: AddressLocalModel
)