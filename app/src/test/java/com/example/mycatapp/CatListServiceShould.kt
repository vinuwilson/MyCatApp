package com.example.mycatapp

import com.example.mycatapp.catinfo.data.api.CatListAPI
import com.example.mycatapp.catinfo.data.api.CatListService
import com.example.mycatapp.catinfo.data.model.CatDetails
import com.example.mycatapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CatListServiceShould : BaseUnitTest() {

    private lateinit var service: CatListService
    private val api: CatListAPI = mock()
    private val catDetails: List<CatDetails> = mock()
    private val expected = Result.success(catDetails)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchCatListFromAPI() = runTest {

        mockSuccessfulCase()

        service.getCatList().first()

        verify(api, times(1)).fetchCatList()
    }

    @Test
    fun convertValuesToFlowResultAndEmits() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, service.getCatList().first())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runTest {

        mockFailureCase()

        assertEquals(exception.message, service.getCatList().first().exceptionOrNull()!!.message)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchCatList()).thenReturn(catDetails)

        service = CatListService(api)
    }

    private suspend fun mockFailureCase() {
        whenever(api.fetchCatList()).thenThrow(RuntimeException("Backend Error"))

        service = CatListService(api)
    }
}