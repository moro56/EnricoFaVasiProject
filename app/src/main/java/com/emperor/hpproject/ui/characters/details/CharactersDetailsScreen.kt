package com.emperor.hpproject.ui.characters.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.emperor.hpproject.domain.models.HPCharacter
import com.emperor.hpproject.ui.theme.AppTheme
import com.emperor.hpproject.utils.hpCharacterMock

@Composable
fun CharactersDetailsScreen(
    modifier: Modifier,
    viewModel: CharactersDetailsViewModel = hiltViewModel()
) {
    // ViewModel state
    val uiState = viewModel.uiState.collectAsState()

    CharactersListScreenContent(
        modifier = modifier,
        character = uiState.value.character,
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreenContent(
    modifier: Modifier,
    character: HPCharacter?
) {
    Column(modifier = modifier) {
        if (character != null) {

        } else {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CharactersListScreenContentPreview() {
    AppTheme {
        CharactersListScreenContent(
            Modifier.fillMaxSize(),
            hpCharacterMock
        )
    }
}
