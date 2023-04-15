package com.emperor.hpproject.ui.list

import android.content.Context
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.emperor.hpproject.R
import com.emperor.hpproject.ui.characters.list.CharactersListContract
import com.emperor.hpproject.ui.characters.list.CharactersListScreen
import com.emperor.hpproject.ui.characters.list.CharactersListViewModel
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
class CharactersListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `student list shows correctly`() {
        every { viewModel.uiState } returns MutableStateFlow(
            CharactersListContract.State(
                studentList = mapOf(
                    "house1" to listOf(
                        hpCharacterMock
                    ),
                    "house2" to listOf(
                        hpCharacterMock.copy(id = "id2", name = "Other Name")
                    )
                ),
                staffList = listOf(),
                searchedList = listOf(),
                loading = false
            )
        ).asStateFlow()

        composeTestRule.setContent {
            AppTheme {
                CharactersListScreen(modifier = Modifier, viewModel = viewModel)
            }
        }

        composeTestRule.onNodeWithText("house1".uppercase()).assertIsDisplayed()
        composeTestRule.onNodeWithText("house2".uppercase()).assertIsDisplayed()
        composeTestRule.onNodeWithText(hpCharacterMock.name).assertIsDisplayed()
        composeTestRule.onNodeWithText("Other Name").assertIsDisplayed()
    }

    @Test
    fun `staff list shows correctly`() {
        every { viewModel.uiState } returns MutableStateFlow(
            CharactersListContract.State(
                studentList = mapOf(),
                staffList = listOf(hpCharacterMock),
                searchedList = listOf(),
                loading = false
            )
        ).asStateFlow()

        composeTestRule.setContent {
            AppTheme {
                CharactersListScreen(modifier = Modifier, viewModel = viewModel)
            }
        }

        val context: Context = ApplicationProvider.getApplicationContext()
        val staffButton = context.getString(R.string.character_staff)
        composeTestRule.onNodeWithText(staffButton).performClick()
        composeTestRule.onNodeWithText(hpCharacterMock.name).assertIsDisplayed()
    }

    /** MOCKS */

    private val viewModel: CharactersListViewModel = mockk()
}