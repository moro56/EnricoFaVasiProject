package com.emperor.hpproject.ui.characters.list

import androidx.lifecycle.viewModelScope
import com.emperor.hpproject.domain.Repository
import com.emperor.hpproject.domain.models.DomainResponse
import com.emperor.hpproject.utils.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel<CharactersListContract.Event, CharactersListContract.State, CharactersListContract.Effect>() {

    init {
        loadCharacters()
    }

    override fun createInitialState() = CharactersListContract.State(
        studentList = mapOf(),
        staffList = listOf(),
        searchedList = listOf(),
        loading = false
    )

    override fun handleEvent(event: CharactersListContract.Event) {
        when (event) {
            is CharactersListContract.Event.SearchCharacters -> searchCharacters(event.value)
        }
    }

    /**
     * Load all characters
     */
    private fun loadCharacters() = viewModelScope.launch {
        setState { copy(loading = true) }
        repository.observeAllCharacters().collectLatest { list ->
            setState {
                copy(
                    loading = false,
                    studentList = list.filter { it.hogwartsStudent }
                        .groupBy { it.house },
                    staffList = list.filter { it.hogwartsStaff }
                )
            }
        }
    }

    /**
     * Search characters
     *
     * @param value search param
     */
    private fun searchCharacters(value: String) = viewModelScope.launch {
        when (val filteredCharacters = repository.filterCharacters(value.trim())) {
            is DomainResponse.Error -> {
                // TODO show SnackBar
            }
            is DomainResponse.Success -> setState { copy(searchedList = filteredCharacters.result) }
        }
    }
}