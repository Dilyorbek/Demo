package uz.msit.demo.users.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import uz.msit.demo.core.utils.Left
import uz.msit.demo.core.utils.Right
import uz.msit.demo.core.utils.left
import uz.msit.demo.core.utils.right
import uz.msit.demo.users.MainCoroutineRule
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.model.UserDetails
import uz.msit.demo.users.domain.repository.UserRepository
import uz.msit.demo.utils.TestData

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
internal class GetUserDetailsUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockRepository: UserRepository
    private lateinit var sut: GetUserDetailsUseCase

    private val userDetails = TestData.testUserDetails

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sut = GetUserDetailsUseCase(mockRepository)
    }

    @Test
    fun `when success should return correct users`() = runTest {
        given(mockRepository.getUserDetails(userDetails.login)).willReturn(flow { emit(Right(userDetails)) })

        val response = sut(userDetails.login).last().right()

        Mockito.verify(mockRepository, Mockito.times(1)).getUserDetails(userDetails.login)
        assertEquals(userDetails, response)
    }

    @Test
    fun `when failure should return failure`() = runTest {
        val expected = GetUsersFailure.UnknownError
        given(mockRepository.getUserDetails(userDetails.login)).willReturn(flow { emit(Left(expected)) })

        val response =  sut(userDetails.login).first().left()

        Mockito.verify(mockRepository, Mockito.times(1)).getUserDetails(userDetails.login)
        assertEquals(expected, response)
    }
}