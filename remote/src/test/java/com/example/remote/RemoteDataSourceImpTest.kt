package com.example.remote

import androidx.test.filters.SmallTest
import com.example.data.repository.RemoteDataSource
import com.example.remote.api.ApiService
import com.example.remote.mapper.AuthorNetworkDataMapper
import com.example.remote.mapper.PostNetworkDataMapper
import com.example.remote.source.RemoteDataSourceImp
import com.google.common.truth.Truth
import com.example.remote.utils.TestDataGenerator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class RemoteDataSourceImpTest {

    @MockK
    private lateinit var apiService : ApiService
    private val authorNetworkDataMapper = AuthorNetworkDataMapper()
    private val postNetworkDataMapper = PostNetworkDataMapper()

    private lateinit var remoteDataSource : RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        remoteDataSource = RemoteDataSourceImp(
            apiService = apiService,
            authorMapper = authorNetworkDataMapper,
            postMapper = postNetworkDataMapper
        )
    }

    @Test
    fun test_get_Author_success() = runBlockingTest {

        val authorNetwork = TestDataGenerator.generateAuthors()

        // Given
        coEvery { apiService.getAuthors() } returns authorNetwork

        // When
        val result = remoteDataSource.getAuthors()

        // Then
        coVerify { apiService.getAuthors() }

        // Assertion
        val expected = authorNetworkDataMapper.fromList(authorNetwork)
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_Author_fail() = runBlockingTest {

        // Given
        coEvery { apiService.getAuthors() } throws Exception()

        // When
        remoteDataSource.getAuthors()

        // Then
        coVerify { apiService.getAuthors() }

    }
}