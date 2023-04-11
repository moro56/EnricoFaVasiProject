package com.emperor.hpproject.data.network

import com.emperor.hpproject.data.network.services.ApiService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class RestApiTest {

    private lateinit var restApi: RestApi

    @Before
    fun setUp() {
        restApi = RestApiImpl(apiService)
    }

    @Test
    fun `load characters is successful`() = runTest {
        coEvery { apiService.loadCharacters() } returns Response.success(listOf(mockk(), mockk()))

        val result = restApi.loadAllCharacters()

        Assert.assertNotNull(result)
        Assert.assertEquals(2, result.size)
    }

    @Test
    fun `load characters is successful but body is null`() = runTest {
        coEvery { apiService.loadCharacters() } returns Response.success(null)

        val result = restApi.loadAllCharacters()

        Assert.assertNotNull(result)
        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun `load characters fails`() = runTest {
        coEvery { apiService.loadCharacters() } returns Response.error(
            400,
            mockk {
                every { contentType() } returns mockk()
                every { contentLength() } returns 0L
            }
        )

        try {
            restApi.loadAllCharacters()
            Assert.fail()
        } catch (e: Exception) {
            Assert.assertTrue(true)
        }
    }

    /** MOCKS */

    private val apiService: ApiService = mockk()
}