package com.example.local.utils

import com.example.local.model.AuthorLocalModel

/**
 * Dummy data generator for tests
 */
class TestDataGenerator {

    companion object {
        fun generateAuthorItems() : List<AuthorLocalModel> {
            val item1 =  AuthorLocalModel(1, "Mohamed", "Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","image")
            val item2 =  AuthorLocalModel(1, "Mohamed", "Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","image")
            val item3 =  AuthorLocalModel(1, "Mohamed", "Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","image")
            return listOf(item1, item2, item3)
        }

        fun generateAuthorItem() : AuthorLocalModel {
            return AuthorLocalModel(1, "Mohamed", "Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","image")
        }
    }

}