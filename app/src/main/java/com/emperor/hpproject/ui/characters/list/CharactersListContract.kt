package com.emperor.hpproject.ui.characters.list

import com.emperor.hpproject.domain.models.HPCharacter
import com.emperor.hpproject.utils.mvi.models.UiEffect
import com.emperor.hpproject.utils.mvi.models.UiEvent
import com.emperor.hpproject.utils.mvi.models.UiState

class CharactersListContract {

    sealed class Event : UiEvent

    /**
     * State
     *
     * @param studentList list of student characters
     * @param staffList list of staff characters
     * @param loading is loading
     */
    data class State(
        val studentList: Map<String, List<HPCharacter>>,
        val staffList: List<HPCharacter>,
        val loading: Boolean
    ) : UiState

    sealed class Effect : UiEffect
}