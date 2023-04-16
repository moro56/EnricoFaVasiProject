package com.emperor.hpproject.ui.characters.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.emperor.hpproject.R
import com.emperor.hpproject.domain.models.HPCharacter
import com.emperor.hpproject.ui.characters.details.components.CharacterInfo
import com.emperor.hpproject.ui.theme.AppTheme
import com.emperor.hpproject.utils.hpCharacterMock

@Composable
fun CharactersDetailsScreen(
    modifier: Modifier,
    viewModel: CharactersDetailsViewModel = hiltViewModel()
) {
    // ViewModel state
    val uiState = viewModel.uiState.collectAsState()

    CharactersDetailsScreenContent(
        modifier = modifier,
        character = uiState.value.character,
    )
}

@Composable
fun CharactersDetailsScreenContent(
    modifier: Modifier,
    character: HPCharacter?
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
    ) {
        IconButton(onClick = {

        }) {
            Icon(Icons.Outlined.ArrowBack, contentDescription = "Back")
        }
        if (character != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    AsyncImage(
                        modifier = Modifier
                            .size(180.dp)
                            .clip(CircleShape),
                        model = "",//character.image,
                        contentDescription = "Character image",
                        placeholder = painterResource(id = R.drawable.ic_load_placeholder),
                        error = painterResource(id = R.drawable.ic_load_error),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(text = character.name, style = MaterialTheme.typography.titleLarge)
                if (character.alternateNames.isNotEmpty()) {
                    Text(
                        text = character.alternateNames.joinToString(", "),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_species),
                    value = character.species,
                    modifier = Modifier.padding(top = 8.dp)
                )
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_gender),
                    value = character.gender
                )
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_house),
                    value = character.house
                )
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_date_of_birth),
                    value = character.dateOfBirth ?: "-"
                )
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_wizard),
                    value = if (character.wizard) stringResource(id = R.string.yes).lowercase() else stringResource(
                        id = R.string.no
                    ).lowercase()
                )
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_ancestry),
                    value = character.ancestry
                )
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_eye_colour),
                    value = character.eyeColour
                )
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_hair_colour),
                    value = character.hairColour
                )
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_wand),
                    value = character.wandString
                )
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_patronus),
                    value = character.patronus
                )
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_actor),
                    value = character.actor
                )
                if (character.alternate_actors.isNotEmpty()) {
                    CharacterInfo(
                        key = stringResource(id = R.string.character_info_alternate_actors),
                        value = character.alternate_actors.joinToString(", ")
                    )
                }
                CharacterInfo(
                    key = stringResource(id = R.string.character_info_alive),
                    value = if (character.alive) stringResource(id = R.string.yes).lowercase() else stringResource(
                        id = R.string.no
                    ).lowercase()
                )
            }
        } else {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CharactersDetailsScreenContentPreview() {
    AppTheme {
        CharactersDetailsScreenContent(
            Modifier.fillMaxSize(),
            hpCharacterMock
        )
    }
}
