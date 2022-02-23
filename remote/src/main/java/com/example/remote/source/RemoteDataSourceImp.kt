package com.example.remote.source

import com.example.common.Mapper
import com.example.data.repository.RemoteDataSource
import com.example.remote.api.ApiService
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(
    private val apiService : ApiService,
    private val mapper : Mapper<Any, Any>,
    ) : RemoteDataSource {


}