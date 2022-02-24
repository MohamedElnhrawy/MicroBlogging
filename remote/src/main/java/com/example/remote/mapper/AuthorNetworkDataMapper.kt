package com.example.remote.mapper

import com.example.common.Mapper
import com.example.data.model.AddressDTO
import com.example.data.model.AuthorDTO
import com.example.remote.model.AuthorResponseNetwork
import javax.inject.Inject


class AuthorNetworkDataMapper @Inject constructor() :
    Mapper<AuthorResponseNetwork, AuthorDTO> {

    override fun from(i: AuthorResponseNetwork?): AuthorDTO {
        return AuthorDTO(
            id = i?.id ?: 0,
            name = i?.name ?: "",
            userName = i?.userName ?: "",
            email = i?.email ?: "",
            avatarUrl = i?.avatarUrl ?: "",
//            address = AddressDTO(i?.address?.latitude ?: 0.0,i?.address?.longitude ?: 0.0),
        )
    }

    override fun to(o: AuthorDTO?): AuthorResponseNetwork {
        return AuthorResponseNetwork()
    }
}