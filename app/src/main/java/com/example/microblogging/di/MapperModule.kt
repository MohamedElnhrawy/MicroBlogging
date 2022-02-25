package com.example.microblogging.di

import com.example.common.Mapper
import com.example.data.mapper.AuthorDataDomainMapper
import com.example.data.mapper.PostDataDomainMapper
import com.example.data.model.AuthorDTO
import com.example.data.model.PostDTO
import com.example.domain.entity.AuthorEntity
import com.example.domain.entity.PostEntity
import com.example.feature.mapper.AuthorDomainUiMapper
import com.example.feature.mapper.PostDomainUiMapper
import com.example.feature.model.AuthorUiModel
import com.example.feature.model.PostUiModel
import com.example.local.mapper.AuthorLocalDataMapper
import com.example.local.model.AuthorLocalModel
import com.example.remote.mapper.AuthorNetworkDataMapper
import com.example.remote.mapper.PostNetworkDataMapper
import com.example.remote.model.AuthorResponseNetwork
import com.example.remote.model.PostResponseNetwork
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
    @Binds
    abstract fun bindsPostDataDomainMapper(mapper : PostDataDomainMapper) : Mapper<PostDTO, PostEntity>

    //endregion


    //region Presentation Mappers
    @Binds
    abstract fun bindsAuthorDomainUiMapper(mapper : AuthorDomainUiMapper) : Mapper<AuthorEntity, AuthorUiModel>
    @Binds
    abstract fun bindsPostDomainUiMapper(mapper : PostDomainUiMapper) : Mapper<PostEntity, PostUiModel>
    //endregion


    //region Remote Mappers
    @Binds
    abstract fun bindsAuthorNetworkDataMapper(mapper: AuthorNetworkDataMapper): Mapper<AuthorResponseNetwork, AuthorDTO>
    @Binds
    abstract fun bindsPostNetworkDataMapper(mapper: PostNetworkDataMapper): Mapper<PostResponseNetwork, PostDTO>
    //endregion
}