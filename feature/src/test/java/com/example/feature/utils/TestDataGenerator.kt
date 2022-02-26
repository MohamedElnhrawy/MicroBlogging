package com.example.feature.utils

import com.example.domain.entity.AuthorEntity

class TestDataGenerator {
    companion object {
        fun generateAuthorItems() : List<AuthorEntity>{
            return listOf(
                AuthorEntity(1,"Mohamed","Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","imgUrl")
            )
        }
    }

}