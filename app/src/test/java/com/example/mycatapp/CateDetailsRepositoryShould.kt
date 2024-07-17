package com.example.mycatapp

import com.example.mycatapp.catinfo.data.api.CatListService
import com.example.mycatapp.catinfo.data.api.CateDetailsRepositoryImp
import com.example.mycatapp.catinfo.data.model.CatDetails
import com.example.mycatapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CateDetailsRepositoryShould : BaseUnitTest() {

    private lateinit var repository: CateDetailsRepositoryImp
    private val service: CatListService = mock()
    private val catDetails: List<CatDetails> = mock()
    private val expected = Result.success(catDetails)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchCatListFromService() = runTest {

        mockSuccessfulCase()

        repository.getCatList()

        verify(service, times(1)).getCatList()
    }

    @Test
    fun emitCatListFromService() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, repository.getCatList().first())
    }

    @Test
    fun emitErrorWhenReceiveError() = runTest {

        mockFailureCase()

        assertEquals(exception, repository.getCatList().first().exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase() {

        whenever(service.getCatList()).thenReturn(
            flow {
                emit(expected)
            }
        )

        repository = CateDetailsRepositoryImp(service)
    }

    private suspend fun mockFailureCase() {

        whenever(service.getCatList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        repository = CateDetailsRepositoryImp(service)
    }
}