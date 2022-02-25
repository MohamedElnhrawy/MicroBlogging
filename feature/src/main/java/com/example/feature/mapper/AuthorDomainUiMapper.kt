package com.example.feature.mapper

import com.example.common.Mapper
import com.example.domain.entity.AuthorEntity
import com.example.feature.model.AuthorUiModel
import javax.inject.Inject


class AuthorDomainUiMapper @Inject constructor() : Mapper<AuthorEntity, AuthorUiModel> {


    override fun from(i: AuthorEntity?): AuthorUiModel {
        return  AuthorUiModel(
            id = i?.id?:0,
            name = i?.name?:"",
            userName = i?.userName ?: "",
            email = i?.email ?: "",
            avatarUrl = i?.avatarUrl ?: "",
        )
    }

    override fun to(o: AuthorUiModel?): AuthorEntity {
        return  AuthorEntity(
            id = o?.id?:0,
            name = o?.name?:"",
            userName = o?.userName ?: "",
            email = o?.email ?: "",
            avatarUrl = o?.avatarUrl ?: "",
        )
    }
}