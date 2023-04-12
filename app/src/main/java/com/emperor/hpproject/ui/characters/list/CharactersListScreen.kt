package com.emperor.hpproject.ui.characters.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.emperor.hpproject.R
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
        studentList = uiState.value.studentList,
        staffList = uiState.value.staffList,
        loading = uiState.value.loading
    )
}

@Composable
fun CharactersListScreenContent(
    modifier: Modifier,
    studentList: List<HPCharacter>,
    staffList: List<HPCharacter>,
    loading: Boolean
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabData = listOf(
        stringResource(id = R.string.character_students),
        stringResource(id = R.string.character_staff)
    )

    val currentList = if (tabIndex == 0) studentList else staffList
    Column(modifier = modifier.statusBarsPadding()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabData.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index, onClick = {
                    tabIndex = index
                }, text = {
                    Text(text = text)
                })
            }
        }
        LazyColumn {
            items(currentList, key = { item -> item.id }) {
                CharacterItem(character = it)
            }
            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CharactersListScreenContentPreview() {
    AppTheme {
        CharactersListScreenContent(
            Modifier.fillMaxSize(),
            listOf(hpCharacterMock),
            listOf(),
            false
        )
    }
}
