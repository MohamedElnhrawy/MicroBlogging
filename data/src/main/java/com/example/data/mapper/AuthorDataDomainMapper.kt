package com.example.data.mapper

import com.example.common.Mapper
import com.example.data.model.AddressDTO
import com.example.data.model.AuthorDTO
import com.example.domain.entity.AddressEntity
import com.example.domain.entity.AuthorEntity
import javax.inject.Inject


class AuthorDataDomainMapper @Inject constructor() :
    Mapper<AuthorDTO, AuthorEntity> {

    override fun from(i: AuthorDTO?): AuthorEntity {
        return AuthorEntity(
            id = i?.id ?: -1,
            name = i?.name ?: "",
            userName = i?.userName ?: "",
            email = i?.email ?: "",
            avatarUrl = i?.avatarUrl ?: "",
//            address = AddressEntity(i?.address?.latitude ?: 0.0 , i?.address?.longitude ?: 0.0)

        )
    }


    override fun to(o: AuthorEntity?): AuthorDTO {
        return AuthorDTO(
            id = o?.id ?: -1,
            name = o?.name ?: "",
            userName = o?.userName ?: "",
            email = o?.email ?: "",
            avatarUrl = o?.avatarUrl ?: "",
//            address = AddressDTO(o?.address?.latitude ?: 0.0 , o?.address?.longitude ?: 0.0)

        )
    }
}