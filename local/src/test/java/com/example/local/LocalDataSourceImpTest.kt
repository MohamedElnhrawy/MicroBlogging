package com.example.local

import androidx.test.filters.SmallTest
import com.example.data.repository.LocalDataSource
import com.example.local.database.AuthorDAO
import com.example.local.mapper.AuthorLocalDataMapper
import com.example.local.source.LocalDataSourceImp
import com.example.local.utils.TestData
import com.google.common.truth.Truth
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
class LocalDataSourceImpTest {

    @MockK
    private lateinit var authorDAO: AuthorDAO

    private val authorLocalDataMapper = AuthorLocalDataMapper()
    private lateinit var localDataSource : LocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create LocalDataSourceImp before every test
        localDataSource = LocalDataSourceImp(
            authorDAO = authorDAO,
            authorMapper = authorLocalDataMapper
        )
    }

    @Test
    fun test_add_author_item_success() = runBlockingTest {

        val authorsLocal = TestData.generateAuthorItems()
        val expected = listOf<Long>(1L)

        // Given
        coEvery { authorDAO.addAuthorItems(any()) } returns expected

        // When
        val returned = localDataSource.addItems(authorLocalDataMapper.fromList(list = authorsLocal))

        // Then
        coVerify { authorDAO.addAuthorItems(any()) }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_add_author_item_fail() = runBlockingTest {

        val authorItems = authorLocalDataMapper.fromList( list = TestData.generateAuthorItems())

        // Given
        coEvery { authorDAO.addAuthorItems(any()) } throws Exception()

        // When
        localDataSource.addItems(authorItems)

        // Then
        coVerify { authorDAO.addAuthorItems(any()) }

    }

    @Test
    fun test_get_author_item_success() = runBlockingTest {

        val auhtorLocal = TestData.generateAuthorItem()
        val expected = authorLocalDataMapper.from(i = auhtorLocal)

        // Given
        coEvery { authorDAO.getAuthorItem(any()) } returns auhtorLocal

        // When
        val returned = localDataSource.getItem(auhtorLocal.id)

        // Then
        coVerify { authorDAO.getAuthorItem(any()) }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_author_item_fail() = runBlockingTest {

        val authorItem = authorLocalDataMapper.from(i = TestData.generateAuthorItem())

        // Given
        coEvery { authorDAO.getAuthorItem(any()) } throws Exception()

        // When
        localDataSource.getItem(authorItem.id)

        // Then
        coVerify { authorDAO.getAuthorItem(any()) }

    }

    @Test
    fun test_add_author_items_success() = runBlockingTest {

        val authorItems = authorLocalDataMapper.fromList(list = TestData.generateAuthorItems())
        val expected = MutableList(authorItems.size) { index -> index.toLong() }

        // Given
        coEvery { authorDAO.addAuthorItems(any()) } returns expected

        // When
        val returned = localDataSource.addItems(authorItems)

        // Then
        coVerify { authorDAO.addAuthorItems(any()) }

        // Assertion
        Truth.assertThat(returned).hasSize(expected.size)
    }

    @Test(expected = Exception::class)
    fun test_add_author_items_fail() = runBlockingTest {

        val authorItems = authorLocalDataMapper.fromList(list = TestData.generateAuthorItems())

        // Given
        coEvery { authorDAO.addAuthorItems(any()) } throws Exception()

        // When
        localDataSource.addItems(authorItems)

        // Then
        coVerify { authorDAO.addAuthorItems(any()) }

    }

    @Test
    fun test_get_author_items_success() = runBlockingTest {

        val authorItems = TestData.generateAuthorItems()
        val expected = authorLocalDataMapper.fromList(list = authorItems)

        // Given
        coEvery { authorDAO.getAuthorItems() } returns authorItems

        // When
        val returned = localDataSource.getItems()

        // Then
        coVerify { authorDAO.getAuthorItems() }

        // Assertion
        Truth.assertThat(returned).containsExactlyElementsIn(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_author_items_fail() = runBlockingTest {

        // Given
        coEvery { authorDAO.getAuthorItems() } throws Exception()

        // When
        localDataSource.getItems()

        // Then
        coVerify { authorDAO.getAuthorItems() }

    }

    @Test
    fun test_update_author_item_success() = runBlockingTest {

        val authorItem = authorLocalDataMapper.from(i = TestData.generateAuthorItem())
        val expected = 1

        // Given
        coEvery { authorDAO.updateAuthorItem(any()) } returns expected

        // When
        val returned = localDataSource.updateItem(authorItem)

        // Then
        coVerify { authorDAO.updateAuthorItem(any()) }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)

    }

    @Test(expected = Exception::class)
    fun test_update_author_item_fail() = runBlockingTest {

        val authorItem = authorLocalDataMapper.from(i = TestData.generateAuthorItem())

        // Given
        coEvery { authorDAO.updateAuthorItem(any()) } throws  Exception()

        // When
        localDataSource.updateItem(authorItem)

        // Then
        coVerify { authorDAO.updateAuthorItem(any()) }

    }

    @Test
    fun test_delete_author_item_by_id_success() = runBlockingTest {

        val expected = 1

        // Given
        coEvery { authorDAO.deleteAuthorItemById(any()) } returns expected

        // When
        val returned = localDataSource.deleteItemById(1)

        // Then
        coVerify { authorDAO.deleteAuthorItemById(any()) }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)

    }

    @Test(expected = Exception::class)
    fun test_delete_author_item_by_id_fail() = runBlockingTest {

        // Given
        coEvery { authorDAO.deleteAuthorItemById(any()) } throws Exception()

        // When
        localDataSource.deleteItemById(1)

        // Then
        coVerify { authorDAO.deleteAuthorItemById(any()) }

    }

    @Test
    fun test_delete_author_item_success() = runBlockingTest {

        val authorItem = authorLocalDataMapper.from(i = TestData.generateAuthorItem())
        val expected = 1

        // Given
        coEvery { authorDAO.deleteAuthorItem(any()) } returns expected

        // When
        val returned = localDataSource.deleteItem(authorItem)

        // Then
        coVerify { authorDAO.deleteAuthorItem(any()) }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun test_delete_author_item_fail() = runBlockingTest {

        val authorItem = authorLocalDataMapper.from(i = TestData.generateAuthorItem())

        // Given
        coEvery { authorDAO.deleteAuthorItem(any()) } throws Exception()

        // When
        localDataSource.deleteItem(authorItem)

        // Then
        coVerify { authorDAO.deleteAuthorItem(any()) }

    }

    @Test
    fun test_clear_all_authors_success() = runBlockingTest {

        val authorItems = authorLocalDataMapper.fromList(list = TestData.generateAuthorItems())
        val expected = authorItems.size // Affected row count

        // Given
        coEvery { authorDAO.clearCachedAuthorItems() } returns expected

        // When
        val returned = localDataSource.clearCachedItems()

        // Then
        coVerify { authorDAO.clearCachedAuthorItems() }

        // Assertion
        Truth.assertThat(returned).isEqualTo(expected)

    }

    @Test(expected = Exception::class)
    fun test_clear_all_authors_fail() = runBlockingTest {

        // Given
        coEvery { authorDAO.clearCachedAuthorItems() } throws Exception()

        // When
        localDataSource.clearCachedItems()

        // Then
        coVerify { authorDAO.clearCachedAuthorItems() }

    }
}