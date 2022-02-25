package com.example.domain.entity


data class PostEntity(
    val id: Int,
    val authorId: Int ,
    val date: String?,
    val title: String?,
    val body: String?,
    val imageUrl: String?,

)