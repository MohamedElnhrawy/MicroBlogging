package com.example.data.utils

import com.example.data.model.AuthorDTO


class TestDataGenerator {

    companion object {
        fun generateAuthor() : AuthorDTO {
            return AuthorDTO(1, "Mohamed", "Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","image")
        }



        fun generateAuthorItems() : List<AuthorDTO> {
            return listOf(
                AuthorDTO(1, "Mohamed", "Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","image"),
            )
        }

    }

}