package com.example.remote.utils

import com.example.remote.model.*

/**
 * Dummy data generator for tests
 */
class TestDataGenerator {

    companion object {
        fun generateAuthors(): List<AuthorResponseNetwork> {
            return listOf(
                AuthorResponseNetwork(1,"Mohamed","Mohamed Alnahrawy","mohamedelnhrawy64@gmail.com","imgUrl")
            )
}
}

}