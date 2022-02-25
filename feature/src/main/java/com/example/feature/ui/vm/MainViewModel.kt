package com.example.feature.ui.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.common.Mapper
import com.example.common.Resource
import com.example.domain.entity.AuthorEntity
import com.example.domain.usecase.GetAuthorsUseCase
import com.example.feature.model.AuthorUiModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import com.example.feature.ui.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getAuthorsUseCase: GetAuthorsUseCase,
    private val authorMapper : Mapper<AuthorEntity, AuthorUiModel>
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>(){

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            authorState = MainContract.AuthorsState.Idle,
            selectedAuthor = null
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnFetchAuthors -> {
                fetchAuthors()
            }
            is MainContract.Event.OnAuthorItemClicked -> {
                val item = event.author
                setSelectedAuthor(author = item)
            }
        }
    }

    /**
     * Fetch authors
     */
    private fun fetchAuthors() {
        viewModelScope.launch {
            getAuthorsUseCase.execute(null)
                    .onStart {
                        emit(Resource.Loading)
                    }
                    .collect {
                        when (it) {
                            is Resource.Loading -> {
                                // Set State
                                setState { copy(authorState = MainContract.AuthorsState.Loading)
                                }
                            }
                            is Resource.Empty -> {
                                // Set State
                                setState { copy(authorState = MainContract.AuthorsState.Idle) }
                            }
                            is Resource.Success -> {
                                // Set State
                                val authorList = authorMapper.fromList(it.data)
                                setState {
                                    copy(
                                        authorState = MainContract.AuthorsState.Success(
                                            authorList = authorList
                                        )
                                    )
                                }
                            }
                            is Resource.Error -> {
                                // Set Effect
                                setState { copy(authorState = MainContract.AuthorsState.Idle)}
                                setEffect { MainContract.Effect.ShowError(message = it.exception.message) }
                            }
                        }
                    }

        }
    }

    /**
     * Set selected author item
     */
    private fun setSelectedAuthor(author : AuthorUiModel?) {
        // Set State
        setState { copy(selectedAuthor = author) }
    }

    init {
        setEvent(MainContract.Event.OnFetchAuthors)

    }
}