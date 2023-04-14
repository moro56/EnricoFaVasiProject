package com.emperor.hpproject.ui

import androidx.lifecycle.viewModelScope
import com.emperor.hpproject.domain.Repository
import com.emperor.hpproject.domain.models.DomainResponse
import com.emperor.hpproject.utils.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    override fun createInitialState() = MainContract.State

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            MainContract.Event.DownloadCharacters -> downloadCharacters()
        }
    }

    private fun downloadCharacters() = viewModelScope.launch {
        when (val response = repository.downloadCharacters()) {
            is DomainResponse.Error -> {

            }
            is DomainResponse.Success -> {}
        }
    }
}