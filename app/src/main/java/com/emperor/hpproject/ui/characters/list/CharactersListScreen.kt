package com.emperor.hpproject.ui.characters.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.emperor.hpproject.domain.models.HPCharacter
import com.emperor.hpproject.ui.characters.list.components.CharacterItem
import com.emperor.hpproject.ui.theme.AppTheme
import com.emperor.hpproject.utils.hpCharacterMock

@Composable
fun CharactersListScreen(modifier: Modifier, viewModel: CharactersListViewModel = hiltViewModel()) {
    // ViewModel state
    val uiState = viewModel.uiState.collectAsState()

    CharactersListScreenContent(
        modifier = modifier,
        characterList = uiState.value.characterList,
        loading = uiState.value.loading
    )
}

@Composable
fun CharactersListScreenContent(
    modifier: Modifier,
    characterList: List<HPCharacter>,
    loading: Boolean
) {
    Box(modifier = modifier) {
        LazyColumn {
            items(characterList, key = { item -> item.id }) {
                CharacterItem(character = it)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CharactersListScreenContentPreview() {
    AppTheme {
        CharactersListScreenContent(Modifier.fillMaxSize(), listOf(hpCharacterMock), false)
    }
}
