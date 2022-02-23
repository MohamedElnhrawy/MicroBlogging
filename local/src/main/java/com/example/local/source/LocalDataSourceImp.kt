package com.example.local.source

import com.example.common.Mapper
import com.example.data.repository.LocalDataSource
import javax.inject.Inject

/**
 * Implementation of [LocalDataSource] Source
 */
class LocalDataSourceImp @Inject constructor(
    private val weatherMapper : Mapper<Any, Any>
) : LocalDataSource {



}