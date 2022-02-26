package com.example.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.local.database.AppDatabase
import com.example.local.database.AuthorDAO
import com.google.common.truth.Truth
import com.example.local.utils.TestDataGenerator
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class AuthorDAOTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database : AppDatabase
    private lateinit var authorDao : AuthorDAO

    @Before
    fun setUp() {
        hiltRule.inject()
        authorDao = database.authorDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun test_add_author_item_success() = runBlockingTest {

        val item = TestDataGenerator.generateAuthorItems()

        authorDao.addAuthorItems(item)

        val items = authorDao.getAuthorItems()

        Truth.assertThat(items).contains(item)

    }

    @Test
    fun test_get_author_item_success() = runBlockingTest {

        val item = TestDataGenerator.generateAuthorItems()

        authorDao.addAuthorItems(item)

        val result = authorDao.getAuthorItems()

        Truth.assertThat(item).isEqualTo(result)
    }

    @Test
    fun test_add_and_get_author_items_success() = runBlockingTest {

        val item = TestDataGenerator.generateAuthorItems()

        authorDao.addAuthorItems(item)

        val result = authorDao.getAuthorItems()

        Truth.assertThat(result).containsExactly(item)

    }

    @Test
    fun test_update_author_item_success() = runBlockingTest {

        val item = TestDataGenerator.generateAuthorItem()

        authorDao.addAuthorItem(item)

        val dbItem = authorDao.getAuthorItem(item.id)

        Truth.assertThat(item).isEqualTo(dbItem)

        val updatedItem = item.copy(name = "updated name")

        authorDao.updateAuthorItem(updatedItem)

        val result = authorDao.getAuthorItem(updatedItem.id)

        Truth.assertThat(updatedItem).isEqualTo(result)

    }

    @Test
    fun test_delete_author_item_by_id_success() = runBlockingTest {

        val item = TestDataGenerator.generateAuthorItem()

        authorDao.addAuthorItem(item)

        val dbItem = authorDao.getAuthorItem(item.id)

        Truth.assertThat(item).isEqualTo(dbItem)

        authorDao.deleteAuthorItem(item)

        val items = authorDao.getAuthorItems()

        Truth.assertThat(items).doesNotContain(item)

    }

    @Test
    fun test_delete_author_item_success() = runBlockingTest {

        val item = TestDataGenerator.generateAuthorItem()

        authorDao.addAuthorItem(item)

        val dbItem = authorDao.getAuthorItem(item.id)

        Truth.assertThat(item).isEqualTo(dbItem)

        authorDao.deleteAuthorItem(item)

        val items = authorDao.getAuthorItems()

        Truth.assertThat(items).doesNotContain(item)

    }

    @Test
    fun test_clear_all_authors_success() = runBlockingTest {

        val items = TestDataGenerator.generateAuthorItems()

        authorDao.addAuthorItems(items)

        val dbItems = authorDao.getAuthorItems()

        Truth.assertThat(dbItems).containsExactlyElementsIn(items)

        authorDao.clearCachedAuthorItems()

        val result = authorDao.getAuthorItems()

        Truth.assertThat(result).isEmpty()
    }
}