package com.example.feature.ui.contract


import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.base.UiState
import com.example.feature.model.AuthorUiModel

/**
 * Contract of Main Screen
 */
class MainContract {

    sealed class Event : UiEvent {
        object OnFetchAuthors : Event()
        data class OnAuthorItemClicked(val author : AuthorUiModel) : Event()
    }

    data class State(
        val authorState: AuthorsState,
        val selectedAuthor: AuthorUiModel? = null
    ) : UiState

    sealed class AuthorsState {
        object Idle : AuthorsState()
        object Loading : AuthorsState()
        data class Success(val authorList : List<AuthorUiModel>) : AuthorsState()
    }

    sealed class Effect : UiEffect {

        data class ShowError(val message : String?) : Effect()

    }

}