package com.emperor.hpproject.ui.characters.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.emperor.hpproject.domain.Repository
import com.emperor.hpproject.domain.models.DomainResponse
import com.emperor.hpproject.utils.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : BaseViewModel<CharactersDetailsContract.Event, CharactersDetailsContract.State, CharactersDetailsContract.Effect>() {

    init {
        loadCharacter(savedStateHandle.get<String>("id") ?: "")
    }

    override fun createInitialState() = CharactersDetailsContract.State(character = null)

    override fun handleEvent(event: CharactersDetailsContract.Event) {
    }

    /**
     * Load all characters
     */
    private fun loadCharacter(id: String) = viewModelScope.launch {
        when (val character = repository.getCharacter(id)) {
            is DomainResponse.Error -> {
            }
            is DomainResponse.Success -> setState { copy(character = character.result) }
        }
    }
}