package com.emperor.hpproject.ui.characters.list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.emperor.hpproject.R
import com.emperor.hpproject.domain.models.HPCharacter
import com.emperor.hpproject.ui.theme.AppTheme
import com.emperor.hpproject.utils.hpCharacterMock

@Composable
fun CharacterItem(character: HPCharacter) {
    Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center) {
                AsyncImage(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    model = character.image,
                    contentDescription = "Character image",
                    placeholder = painterResource(id = R.drawable.ic_load_placeholder),
                    error = painterResource(id = R.drawable.ic_load_error),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.padding(horizontal = 6.dp)) {
                Text(text = character.name, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = stringResource(id = R.string.character_is_wizard),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(id = character.isWizardStringResource),
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CharacterItemPreview() {
    AppTheme {
        Box(modifier = Modifier.padding(20.dp)) {
            CharacterItem(hpCharacterMock)
        }
    }
}
