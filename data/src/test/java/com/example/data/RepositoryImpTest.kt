package com.example.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.common.Resource
import com.example.data.mapper.AuthorDataDomainMapper
import com.example.data.mapper.PostDataDomainMapper
import com.example.data.model.AuthorDTO
import com.example.data.repository.LocalDataSource
import com.example.data.repository.RemoteDataSource
import com.example.data.repository.RepositoryImp
import com.google.common.truth.Truth
import com.example.data.utils.TestDataGenerator
import com.example.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class RepositoryImpTest {

    @MockK
    private lateinit var localDataSource: LocalDataSource
    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    private val authorMapper = AuthorDataDomainMapper()
    private val postMapper = PostDataDomainMapper()

    private lateinit var repository : Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RepositoryImp before every test
        repository = RepositoryImp(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            authorMapper = authorMapper,
            postMapper = postMapper
        )
    }

    @Test
    fun test_get_authors_remote_success() = runBlockingTest {

        val authorItems = TestDataGenerator.generateAuthorItems()
        val expected = listOf<Long>(1L)

        // Given
        coEvery { remoteDataSource.getAuthors() } returns authorItems
        coEvery { localDataSource.addItems(authorItems) } returns expected
        coEvery { localDataSource.getItems() } returns listOf(AuthorDTO(id = 1,name = "Mohamed",userName = "Mohamed Alnahrawy",email = "mohamedelnhrawy64@gmail.com",avatarUrl = "image"))


        // When & Assertions
        val flow = repository.getAuthors()

        flow.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
//            Truth.assertThat(expectedData).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData[0]).isEqualTo(authorMapper.fromList(authorItems)[0])
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getAuthors() }
        coVerify { localDataSource.addItems(authorItems) }

    }

    @Test
    fun test_get_authors_remote_fail_local_success() = runBlockingTest {

        val authors = TestDataGenerator.generateAuthorItems()

        // Given
        coEvery { remoteDataSource.getAuthors() } throws Exception()
        coEvery { localDataSource.getItems() } returns authors

        // When && Assertions
        val flow = repository.getAuthors()
        flow.test {
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo(authorMapper.fromList(authors))
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getAuthors() }
        coVerify { localDataSource.getItems() }

    }

    @Test
    fun test_get_authors_remote_fail_local_fail() = runBlockingTest {


        // Given
        coEvery { remoteDataSource.getAuthors() } throws Exception()
        coEvery { localDataSource.getItems() } throws Exception()

        // When && Assertions
        val flow = repository.getAuthors()
        flow.test {
            // Expect Resource.Error
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        // Then
        coVerify { remoteDataSource.getAuthors() }
        coVerify { localDataSource.getItems() }

    }

}