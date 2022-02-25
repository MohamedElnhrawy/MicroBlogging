package com.example.feature.ui.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.common.Mapper
import com.example.common.Resource
import com.example.domain.entity.PostEntity
import com.example.domain.usecase.GetAuthorPostsUseCase
import com.example.feature.model.PostUiModel
import com.example.feature.ui.contract.DetailsContract
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPostsUseCase: GetAuthorPostsUseCase,
    private val postMapper : Mapper<PostEntity, PostUiModel>
) : BaseViewModel<DetailsContract.Event, DetailsContract.State, DetailsContract.Effect>(){

    override fun createInitialState(): DetailsContract.State {
        return DetailsContract.State(postsState = DetailsContract.PostsState.Idle)
    }

    override fun handleEvent(event: DetailsContract.Event) {
        when (event) {
            is DetailsContract.Event.OnFetchPosts -> {
                fetchPosts(event.authorID)
            }

        }
    }

    /**
     * Fetch authors
     */
    private fun fetchPosts(authorID:Int) {
        viewModelScope.launch {
            getPostsUseCase.execute(authorID)
                    .onStart {
                        emit(Resource.Loading)
                    }
                    .collect {
                        when (it) {
                            is Resource.Loading -> {
                                // Set State
                                setState { copy(postsState = DetailsContract.PostsState.Loading)
                                }
                            }
                            is Resource.Empty -> {
                                // Set State
                                setState { copy(postsState = DetailsContract.PostsState.Idle) }
                            }
                            is Resource.Success -> {
                                // Set State
                                val postList = postMapper.fromList(it.data)
                                setState {
                                    copy(
                                        postsState = DetailsContract.PostsState.Success(
                                            postList = postList
                                        )
                                    )
                                }
                            }
                            is Resource.Error -> {
                                // Set Effect
                                setState { copy(postsState = DetailsContract.PostsState.Idle)}
                                setEffect { DetailsContract.Effect.ShowError(message = it.exception.message) }
                            }
                        }
                    }

        }
    }



    init {

    }
}