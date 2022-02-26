package com.example.feature



import com.example.common.Resource
import com.example.feature.ui.contract.MainContract
import com.google.common.truth.Truth
import com.example.feature.utils.MainCoroutineRule
import com.example.feature.utils.TestDataGenerator
import com.example.feature.ui.vm.MainViewModel


import androidx.lifecycle.SavedStateHandle
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.domain.usecase.GetAuthorsUseCase
import com.example.feature.mapper.AuthorDomainUiMapper
//import com.google.common.truth.Truth
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



@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class MainViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    @MockK
    private lateinit var getAuthorUseCase: GetAuthorsUseCase

    private val authorMapper = AuthorDomainUiMapper()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create MainViewModel before every test
        mainViewModel = MainViewModel(
            savedStateHandle = savedStateHandle,
            getAuthorsUseCase = getAuthorUseCase,
            authorMapper = authorMapper
        )
    }

    @Test
    fun test_fetch_authorItems_success() = runBlockingTest {

        val authorItems = TestDataGenerator.generateAuthorItems()
        val authorFlow = flowOf(Resource.Success(authorItems))

        // Given
        coEvery { getAuthorUseCase.execute(any()) } returns authorFlow

        // When && Assertions
        mainViewModel.uiState.test {
            mainViewModel.setEvent(MainContract.Event.OnFetchAuthors)
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorsState.Idle,
                    selectedAuthor = null
                )
            )
            // Expect Resource.Loading
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorsState.Loading,
                    selectedAuthor = null
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = (expected.authorState as MainContract.AuthorsState.Success).authorList
            Truth.assertThat(expected).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorsState.Success(authorMapper.fromList(authorItems)),
                    selectedAuthor = null
                )
            )
            Truth.assertThat(expectedData).containsExactlyElementsIn(authorMapper.fromList(authorItems))


            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getAuthorUseCase.execute(any()) }
    }

    @Test
    fun test_fetch_author_fail() = runBlockingTest {

        val authorErrorFlow = flowOf(Resource.Error(Exception("error string")))

        // Given
        coEvery { getAuthorUseCase.execute(any()) } returns authorErrorFlow

        // When && Assertions (UiState)
        mainViewModel.uiState.test {
            // Call method inside of this scope
            mainViewModel.setEvent(MainContract.Event.OnFetchAuthors)
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorsState.Idle,
                    selectedAuthor = null
                )
            )
            // Expect Resource.Loading
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorsState.Loading,
                    selectedAuthor = null
                )
            )
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // When && Assertions (UiEffect)
        mainViewModel.effect.test {
            // Expect ShowError Effect
            val expected = expectItem()
            val expectedData = (expected as MainContract.Effect.ShowError).message
            Truth.assertThat(expected).isEqualTo(
                MainContract.Effect.ShowError("error string")
            )
            Truth.assertThat(expectedData).isEqualTo("error string")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getAuthorUseCase.execute(any()) }
    }


    @Test
    fun test_select_author_item() = runBlockingTest {

        val author = TestDataGenerator.generateAuthorItems()

        // Given (no-op)

        // When && Assertions
        mainViewModel.uiState.test {
            // Call method inside of this scope
            // For more info, see https://github.com/cashapp/turbine/issues/19
            mainViewModel.setEvent(MainContract.Event.OnAuthorItemClicked(author = authorMapper.fromList(author)[0]))
            // Expect Resource.Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorsState.Idle,
                    selectedAuthor = null
                )
            )
            // Expect Resource.Success
            val expected = expectItem()
            val expectedData = expected.selectedAuthor
            Truth.assertThat(expected).isEqualTo(
                MainContract.State(
                    authorState = MainContract.AuthorsState.Idle,
                    selectedAuthor = authorMapper.fromList(author)[0]
                )
            )
            Truth.assertThat(expectedData).isEqualTo(authorMapper.fromList(author)[0])
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then (no-op)
    }
}