package com.example.mycatapp

import com.example.mycatapp.data.model.CatDetails
import com.example.mycatapp.domain.CatDetailsUseCase
import com.example.mycatapp.presenter.MyCatViewModel
import com.example.mycatapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

class MyCatViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: MyCatViewModel
    private val catDetailsUseCase: CatDetailsUseCase = mock()
    private val catDetails: CatDetails = mock()
    private val expected = Result.success(catDetails)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchCatListFromUseCase() = runTest {

        mockSuccessFulCase()

        viewModel.getCatDetailsList()

        verify(catDetailsUseCase, times(1)).getCatDetailsList()
    }

    @Test
    fun emitCatListFromUseCase() = runTest {

        mockSuccessFulCase()

        assertEquals(expected, viewModel.getCatDetailsList().first())
    }

    @Test
    fun emitErrorWhenReceiveError() = runTest {

        mockFailureCase()

        assertEquals(exception, viewModel.getCatDetailsList().first().exceptionOrNull())
    }

    private suspend fun mockSuccessFulCase() {
        whenever(catDetailsUseCase.getCatDetailsList()).thenReturn(
            flow {
                emit(expected)
            }
        )
        viewModel = MyCatViewModel(catDetailsUseCase)
    }

    private suspend fun mockFailureCase() {
        whenever(catDetailsUseCase.getCatDetailsList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )
        viewModel = MyCatViewModel(catDetailsUseCase)
    }
}