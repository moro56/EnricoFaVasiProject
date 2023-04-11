package com.emperor.hpproject.ui.characters.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.emperor.hpproject.ui.theme.EnricoFaVasiProjectTheme

@Composable
fun CharactersListScreen(modifier: Modifier) {
    CharactersListScreenContent(modifier = modifier)
}

@Composable
fun CharactersListScreenContent(modifier: Modifier) {
    Box(modifier = modifier) {

    }
}

@Composable
@Preview(showBackground = true)
fun CharactersListScreenContentPreview() {
    EnricoFaVasiProjectTheme {
        CharactersListScreenContent(Modifier.fillMaxSize())
    }
}