package com.example.domain

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.common.Resource
import com.example.domain.repository.Repository
import com.example.domain.usecase.GetAuthorsUseCase
import com.google.common.truth.Truth
import com.example.domain.utils.MainCoroutineRule
import com.example.domain.utils.TestDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime


import org.mockito.ArgumentMatchers.any

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class GetAuthorUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var repository: Repository

    private lateinit var getAuthorsUseCase: GetAuthorsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        getAuthorsUseCase = GetAuthorsUseCase(
            repository = repository,
            dispatcher = mainCoroutineRule.dispatcher
        )
    }

    @Test
    fun test_get_author_success() = runBlockingTest {

        val authorsItem = TestDataGenerator.generateAuthorItems()
        val authorFlow = flowOf(Resource.Success(authorsItem))

        // Given
        coEvery { repository.getAuthors() } returns authorFlow

        // When & Assertions
        val result = getAuthorsUseCase.execute(any())
        result.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData[0]).isEqualTo(authorsItem[0])
            expectComplete()
        }

        // Then
        coVerify { repository.getAuthors() }

    }




    @Test
    fun test_get_author_fail() = runBlockingTest {

        val errorFlow = flowOf(Resource.Error(Exception()))

        // Given
        coEvery { repository.getAuthors() } returns errorFlow

        // When & Assertions
        val result = getAuthorsUseCase.execute(null)
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { repository.getAuthors() }

    }



    @Test
    fun test_get_author_fail_pass_paameter_with_null() = runBlockingTest {

        val errorFlow = flowOf(Resource.Error(Exception()))

        // When & Assertions
        val result = getAuthorsUseCase.execute(null)
        result.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }


    }
}