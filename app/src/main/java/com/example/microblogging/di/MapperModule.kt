package com.example.microblogging.di

import com.example.common.Mapper
import com.example.data.mapper.AuthorDataDomainMapper
import com.example.data.model.AuthorDTO
import com.example.domain.entity.AuthorEntity
import com.example.feature.mapper.AuthorDomainUiMapper
import com.example.feature.model.AuthorUiModel
import com.example.local.mapper.AuthorLocalDataMapper
import com.example.local.model.AuthorLocalModel
import com.example.remote.mapper.AuthorNetworkDataMapper
import com.example.remote.model.AuthorResponseNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module that holds Mappers
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    //region Locale Mappers
    @Binds
    abstract fun bindsAuthorLocalDataMapper(mapper : AuthorLocalDataMapper) : Mapper<AuthorLocalModel, AuthorDTO>
    //endregion


    //region Data Mappers
    @Binds
    abstract fun bindsAuthorDataDomainMapper(mapper : AuthorDataDomainMapper) : Mapper<AuthorDTO, AuthorEntity>
    //endregion


    //region Presentation Mappers
    @Binds
    abstract fun bindsAuthorDomainUiMapper(mapper : AuthorDomainUiMapper) : Mapper<AuthorEntity, AuthorUiModel>
    //endregion


    //region Remote Mappers
    @Binds
    abstract fun bindsAuthorNetworkDataMapper(mapper: AuthorNetworkDataMapper): Mapper<AuthorResponseNetwork, AuthorDTO>
    //endregion
}