package com.emperor.hpproject.ui.details

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.emperor.hpproject.ui.characters.details.CharactersDetailsContract
import com.emperor.hpproject.ui.characters.details.CharactersDetailsScreen
import com.emperor.hpproject.ui.characters.details.CharactersDetailsViewModel
import com.emperor.hpproject.ui.theme.AppTheme
import com.emperor.hpproject.utils.TestConstants
import com.emperor.hpproject.utils.hpCharacterMock
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [TestConstants.testSdk],
    qualifiers = TestConstants.testQualifiers,
    instrumentedPackages = [TestConstants.testInstrumentedPackages]
)
class CharactersDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `character info shows correctly`() {
        every { viewModel.uiState } returns MutableStateFlow(
            CharactersDetailsContract.State(character = hpCharacterMock)
        ).asStateFlow()

        composeTestRule.setContent {
            AppTheme {
                CharactersDetailsScreen(modifier = Modifier, onClose = {}, viewModel = viewModel)
            }
        }

        composeTestRule.onNodeWithText(hpCharacterMock.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.alternateNames.joinToString(", "))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.actor).assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.patronus).assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.hairColour).assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.eyeColour).assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.ancestry).assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.house).assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.species).assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.gender).assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.wandString).assertIsDisplayed()
    }

    /** MOCKS */

    private val viewModel: CharactersDetailsViewModel = mockk()
}