package com.emperor.hpproject.domain

import com.emperor.hpproject.data.local.dao.CharacterDao
import com.emperor.hpproject.data.network.RestApi
import com.emperor.hpproject.data.network.models.RemoteHPCharacter
import com.emperor.hpproject.data.network.models.RemoteHPWand
import com.emperor.hpproject.domain.ext.toLocalHPCharacterList
import com.emperor.hpproject.domain.models.DomainResponse
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryTest {

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = RepositoryImpl(restApi, characterDao)
    }

    @Test
    fun `load characters is successful`() = runTest {
        coEvery { characterDao.getAllCharacters() } returns flowOf(
            listOf(
                characterMock1,
                characterMock2
            ).toLocalHPCharacterList()
        )

        val result = repository.observeAllCharacters().single()

        Assert.assertNotNull(result)
        Assert.assertEquals(2, result.size)
    }

    @Test
    fun `load filtered characters is successful`() = runTest {
        coEvery { characterDao.getFilteredCharacters(any()) } returns listOf(
            characterMock1,
            characterMock2
        ).toLocalHPCharacterList()

        val result = repository.filterCharacters("name")

        Assert.assertNotNull(result)
        Assert.assertTrue(result is DomainResponse.Success)
        Assert.assertEquals(2, (result as DomainResponse.Success).result.size)
    }

    @Test
    fun `download characters is successful`() = runTest {
        coEvery { restApi.loadAllCharacters() } returns listOf(characterMock1, characterMock2)
        coJustRun { characterDao.insertAll(any(), any()) }

        val result = repository.downloadCharacters()

        Assert.assertNotNull(result)
        Assert.assertTrue(result is DomainResponse.Success)
    }

    @Test
    fun `download characters fails`() = runTest {
        coEvery { restApi.loadAllCharacters() } throws Exception("error")

        val result = repository.downloadCharacters()

        Assert.assertNotNull(result)
        Assert.assertTrue(result is DomainResponse.Error)
        Assert.assertEquals("error", (result as DomainResponse.Error).exception.message)
    }

    /** MOCKS */

    private val restApi: RestApi = mockk()

    private val characterDao: CharacterDao = mockk()

    private val characterMock1 = RemoteHPCharacter(
        id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
        name = "Harry Potter",
        alternateNames = listOf("The Boy Who Lived", "The Chosen One"),
        species = "human",
        gender = "male",
        house = "Gryffindor",
        dateOfBirth = "31-07-1980",
        yearOfBirth = 1980,
        wizard = true,
        ancestry = "half-blood",
        eyeColour = "green",
        hairColour = "black",
        wand = RemoteHPWand(wood = "holly", core = "phoenix feather", length = 100f),
        patronus = "stag",
        hogwartsStudent = true,
        hogwartsStaff = false,
        actor = "Daniel Radcliffe",
        alternate_actors = listOf(),
        alive = true,
        image = "https://ik.imagekit.io/hpapi/harry.jpg"
    )

    private val characterMock2 = RemoteHPCharacter(
        id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a7",
        name = "Harry Potter",
        alternateNames = listOf("The Boy Who Lived", "The Chosen One"),
        species = "human",
        gender = "male",
        house = "Gryffindor",
        dateOfBirth = "31-07-1980",
        yearOfBirth = 1980,
        wizard = true,
        ancestry = "half-blood",
        eyeColour = "green",
        hairColour = "black",
        wand = RemoteHPWand(wood = "holly", core = "phoenix feather", length = 100f),
        patronus = "stag",
        hogwartsStudent = true,
        hogwartsStaff = false,
        actor = "Daniel Radcliffe",
        alternate_actors = listOf(),
        alive = true,
        image = "https://ik.imagekit.io/hpapi/harry.jpg"
    )
}