package com.emperor.hpproject.ui.characters.list

import com.emperor.hpproject.domain.models.HPCharacter
import com.emperor.hpproject.utils.mvi.models.UiEffect
import com.emperor.hpproject.utils.mvi.models.UiEvent
import com.emperor.hpproject.utils.mvi.models.UiState

class CharactersListContract {

    sealed class Event : UiEvent {
        data class SearchCharacters(val value: String) : Event()
    }

    /**
     * State
     *
     * @param studentList list of student characters
     * @param staffList list of staff characters
     * @param searchedList list of characters filtered by search
     * @param loading is loading
     */
    data class State(
        val studentList: Map<String, List<HPCharacter>>,
        val staffList: List<HPCharacter>,
        val searchedList: List<HPCharacter>,
        val loading: Boolean
    ) : UiState

    sealed class Effect : UiEffect
}