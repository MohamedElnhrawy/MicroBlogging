package com.example.local.mapper

import com.example.common.Mapper
import com.example.data.model.AuthorDTO
import com.example.local.model.AuthorLocalModel
import javax.inject.Inject

class AuthorLocalDataMapper @Inject constructor() : Mapper<AuthorLocalModel, AuthorDTO> {

    override fun from(i: AuthorLocalModel?): AuthorDTO {
        return AuthorDTO(
            id = i?.id ?: -1,
            name = i?.name ?: "",
            userName = i?.userName ?: "",
            email = i?.email ?: "",
            avatarUrl = i?.avatarUrl ?: "",
//            address = AddressDTO(i?.address?.latitude ?: 0.0 , i?.address?.longitude ?: 0.0)

        )
    }

    override fun to(o: AuthorDTO?): AuthorLocalModel {
        return AuthorLocalModel(
            id = o?.id ?: -1,
            name = o?.name ?: "",
            userName = o?.userName ?: "",
            email = o?.email ?: "",
            avatarUrl = o?.avatarUrl ?: "",
//            address = AddressLocalModel(o?.address?.latitude ?: 0.0 , o?.address?.longitude ?: 0.0)
        )
    }
}