package com.emperor.hpproject.ui.characters.list

import androidx.lifecycle.viewModelScope
import com.emperor.hpproject.domain.Repository
import com.emperor.hpproject.domain.models.DomainResponse
import com.emperor.hpproject.utils.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel<CharactersListContract.Event, CharactersListContract.State, CharactersListContract.Effect>() {

    init {
        loadCharacters()
    }

    override fun createInitialState() =
        CharactersListContract.State(characterList = listOf(), loading = false)

    override fun handleEvent(event: CharactersListContract.Event) {
    }

    private fun loadCharacters() = viewModelScope.launch {
        setState { copy(loading = true) }
        when (val response = repository.loadAllCharacters()) {
            is DomainResponse.Error -> setState { copy(loading = false) }
            is DomainResponse.Success -> setState {
                copy(
                    loading = false,
                    characterList = response.result
                )
            }
        }
    }
}