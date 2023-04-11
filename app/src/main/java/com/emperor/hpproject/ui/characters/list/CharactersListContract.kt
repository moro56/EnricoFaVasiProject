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
     * @param characterList list of characters
     * @param loading is loading
     */
    data class State(
        val characterList: List<HPCharacter>,
        val loading: Boolean
    ) : UiState

    sealed class Effect : UiEffect
}