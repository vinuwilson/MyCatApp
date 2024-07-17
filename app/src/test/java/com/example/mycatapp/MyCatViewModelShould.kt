package com.example.mycatapp

import com.example.mycatapp.catinfo.data.model.CatDetails
import com.example.mycatapp.catinfo.domain.CatDetailsUseCase
import com.example.mycatapp.catinfo.presenter.MyCatViewModel
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
    private val catDetails: List<CatDetails> = mock()
    private val expected = Result.success(catDetails)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun fetchCatListFromUseCase() = runTest {

        mockSuccessFulCase()

        viewModel.getCatList()

        verify(catDetailsUseCase, times(1)).getCatList()
    }

    @Test
    fun emitCatListFromUseCase() = runTest {

        mockSuccessFulCase()

        assertEquals(expected, viewModel.getCatList().first())
    }

    @Test
    fun emitErrorWhenReceiveError() = runTest {

        mockFailureCase()

        assertEquals(exception, viewModel.getCatList().first().exceptionOrNull())
    }

    private suspend fun mockSuccessFulCase() {
        whenever(catDetailsUseCase.getCatList()).thenReturn(
            flow {
                emit(expected)
            }
        )
        viewModel = MyCatViewModel(catDetailsUseCase)
    }

    private suspend fun mockFailureCase() {
        whenever(catDetailsUseCase.getCatList()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )
        viewModel = MyCatViewModel(catDetailsUseCase)
    }
}