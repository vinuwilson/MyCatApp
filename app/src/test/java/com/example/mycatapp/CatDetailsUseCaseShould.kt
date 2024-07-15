package com.example.mycatapp

import com.example.mycatapp.data.model.CatDetails
import com.example.mycatapp.domain.CatDetailsUseCase
import com.example.mycatapp.domain.CateDetailsRepository
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

class CatDetailsUseCaseShould : BaseUnitTest() {

    private lateinit var useCase: CatDetailsUseCase
    private val repository : CateDetailsRepository = mock()
    private val catDetails: List<CatDetails> = mock()
    private val expected = Result.success(catDetails)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchCatListFromRepository() = runTest {

        mockSuccessfulCase()

        useCase.getCatList()

        verify(repository, times(1)).getCatList()
    }

    @Test
    fun emitCatListFromRepository() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, useCase.getCatList().first())
    }

    @Test
    fun emitErrorWhenReceiveError() = runTest {

        mockFailureCase()

        assertEquals(exception, useCase.getCatList().first().exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase() {
        whenever(repository.getCatList()).thenReturn(
            flow {
                emit(expected)
            }
        )

        useCase = CatDetailsUseCase(repository)
    }

    private suspend fun mockFailureCase() {
        whenever(repository.getCatList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        useCase = CatDetailsUseCase(repository)
    }
}