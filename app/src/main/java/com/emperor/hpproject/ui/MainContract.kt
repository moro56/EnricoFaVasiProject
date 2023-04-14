package com.emperor.hpproject.ui

import com.emperor.hpproject.utils.mvi.models.UiEffect
import com.emperor.hpproject.utils.mvi.models.UiEvent
import com.emperor.hpproject.utils.mvi.models.UiState

class MainContract {

    sealed class Event : UiEvent {
        /**
         * Download the characters
         */
        object DownloadCharacters: Event()
    }

    object State : UiState

    sealed class Effect : UiEffect
}