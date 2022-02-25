package com.example.remote.model

data class PostResponseNetwork(
    val id: Int = 0,
    val authorId: Int = 0,
    val date: String? = "",
    val title: String? = "",
    val body: String? = "",
    val imageUrl: String? ="",
)
