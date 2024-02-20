package com.emperor.hpproject.domain

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.emperor.hpproject.data.local.dao.CharacterDao
import com.emperor.hpproject.data.local.db.AppDatabase
import com.emperor.hpproject.data.network.RestApi
import com.emperor.hpproject.data.network.models.RemoteHPCharacter
import com.emperor.hpproject.data.network.models.RemoteHPWand
import com.emperor.hpproject.domain.ext.toLocalHPCharacter
import com.emperor.hpproject.domain.models.DomainResponse
import com.emperor.hpproject.utils.TestConstants
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [TestConstants.testSdk],
    qualifiers = TestConstants.testQualifiers,
    instrumentedPackages = [TestConstants.testInstrumentedPackages]
)
class RepositoryTest {

    private lateinit var characterDao: CharacterDao
    private lateinit var db: AppDatabase
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        characterDao = db.characterDao()
        repository = RepositoryImpl(restApi, characterDao)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun `load characters is successful`() = runTest {
        initDb()

        val result = repository.observeAllCharacters().first()

        Assert.assertNotNull(result)
        Assert.assertEquals(3, result.size)
    }

    @Test
    fun `load filtered characters is successful`() = runTest {
        initDb()

        val result = repository.filterCharacters("Harry")

        Assert.assertNotNull(result)
        Assert.assertTrue(result is DomainResponse.Success)
        Assert.assertEquals(2, (result as DomainResponse.Success).result.size)
    }


    @Test
    fun `get specific character is successful`() = runTest {
        initDb()

        val result = repository.getCharacter("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a6")

        Assert.assertNotNull(result)
        Assert.assertTrue(result is DomainResponse.Success)
        Assert.assertEquals(characterMock3.name, (result as DomainResponse.Success).result.name)
    }

    @Test
    fun `download characters is successful`() = runTest {
        coEvery { restApi.loadAllCharacters() } returns listOf(characterMock1, characterMock2)

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

    private suspend fun initDb() {
        characterDao.insertAll(
            characterMock1.toLocalHPCharacter(),
            characterMock2.toLocalHPCharacter(),
            characterMock3.toLocalHPCharacter()
        )
    }

    /** MOCKS */

    private val restApi: RestApi = mockk()

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

    private val characterMock3 = RemoteHPCharacter(
        id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a6",
        name = "Giovanni Mucciaccia",
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