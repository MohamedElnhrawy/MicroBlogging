package com.example.feature.ui.contract


import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.base.UiState
import com.example.feature.model.AuthorUiModel
import com.example.feature.model.PostUiModel

/**
 * Contract of Main Screen
 */
class DetailsContract {

    sealed class Event : UiEvent {
        data class OnFetchPosts(val authorID:Int) : Event()
    }

    data class State(
        val postsState: PostsState,
    ) : UiState

    sealed class PostsState {
        object Idle : PostsState()
        object Loading : PostsState()
        data class Success(val postList : List<PostUiModel>) : PostsState()
    }

    sealed class Effect : UiEffect {

        data class ShowError(val message : String?) : Effect()

    }

}