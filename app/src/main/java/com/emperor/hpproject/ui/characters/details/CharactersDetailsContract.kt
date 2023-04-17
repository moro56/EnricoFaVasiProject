package com.emperor.hpproject.ui.characters.details

import com.emperor.hpproject.domain.models.HPCharacter
import com.emperor.hpproject.utils.mvi.models.UiEffect
import com.emperor.hpproject.utils.mvi.models.UiEvent
import com.emperor.hpproject.utils.mvi.models.UiState

class CharactersDetailsContract {

    sealed class Event : UiEvent

    /**
     * State
     *
     * @param character character info
     */
    data class State(
        val character: HPCharacter?
    ) : UiState

    sealed class Effect : UiEffect
}