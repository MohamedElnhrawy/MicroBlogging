package com.example.domain.utils

import com.example.domain.entity.AuthorEntity

class TestDataGenerator {

    companion object {
        fun generateAuthorItems() : List<AuthorEntity> {
            val item1 =  AuthorEntity(1, "Mohamed", "Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","image")
            val item2 =  AuthorEntity(2, "Mohamed", "Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","image")
            val item3 = AuthorEntity(3, "Mohamed", "Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","image")

            return listOf(item1, item2, item3)
        }


    }

}