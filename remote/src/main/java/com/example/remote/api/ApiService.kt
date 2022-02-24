package com.example.remote.api

import com.example.remote.BuildConfig
import com.example.remote.model.AuthorResponseNetwork
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("authors")
    suspend fun getAuthors() : List<AuthorResponseNetwork>

}