package com.emperor.hpproject.ui.characters.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersListScreenContent(
    modifier: Modifier,
    studentList: Map<String, List<HPCharacter>>,
    staffList: List<HPCharacter>,
    loading: Boolean
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabData = listOf(
        stringResource(id = R.string.character_students),
        stringResource(id = R.string.character_staff)
    )

    Column(modifier = modifier.statusBarsPadding()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            tabData.forEachIndexed { index, text ->
                TextButton(
                    modifier = Modifier.padding(vertical = 12.dp),
                    onClick = { tabIndex = index },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .clip(ButtonDefaults.textShape)
                            .background(if (tabIndex == index) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent)
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        text = text
                    )
                }
            }
        }

        if (loading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        if (tabIndex == 0) {
            // Students
            LazyColumn {
                studentList.forEach { entry ->
                    stickyHeader {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(vertical = 12.dp),
                            text = entry.key.ifBlank { stringResource(id = R.string.character_no_house) }
                                .uppercase(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    items(entry.value, key = { item -> item.id }) {
                        CharacterItem(character = it)
                    }
                    item {
                        Spacer(modifier = Modifier.navigationBarsPadding())
                    }
                }
            }
        } else {
            // Staff
            LazyColumn(contentPadding = PaddingValues(top = 16.dp)) {
                items(staffList, key = { item -> item.id }) {
                    CharacterItem(character = it)
                }
                item {
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }
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
            mapOf("house" to listOf(hpCharacterMock)),
            listOf(),
            false
        )
    }
}
