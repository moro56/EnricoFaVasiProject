package com.emperor.hpproject.utils.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emperor.hpproject.utils.mvi.models.UiEffect
import com.emperor.hpproject.utils.mvi.models.UiEvent
import com.emperor.hpproject.utils.mvi.models.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val currentState: State
        get() = uiState.value

    // State flow
    private val uiStateFlow: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = uiStateFlow.asStateFlow()

    // Event flow
    private val uiEventFlow: MutableSharedFlow<Event> = MutableSharedFlow()
    private val uiEvent = uiEventFlow.asSharedFlow()

    // Effect flow
    private val uiEffectFlow: MutableSharedFlow<Effect> = MutableSharedFlow()
    val uiEffect = uiEffectFlow.asSharedFlow()

    init {
        subscribeEvents()
    }

    /**
     * Listen to events
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            uiEvent.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * Handle events
     * @param event event
     */
    abstract fun handleEvent(event: Event)

    /**
     * Send event
     * @param event event
     */
    fun sendEvent(event: Event) {
        viewModelScope.launch {
            uiEventFlow.emit(event)
        }
    }

    /**
     * Update the state
     * @param reduce reducer
     */
    fun setState(reduce: State.() -> State) {
        uiStateFlow.value = currentState.reduce()
    }

    /**
     * Trigger an effect
     * @param builder effect builder
     */
    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch {
            uiEffectFlow.emit(effectValue)
        }
    }
}
