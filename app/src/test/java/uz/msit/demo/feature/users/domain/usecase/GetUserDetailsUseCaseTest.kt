package uz.msit.demo.feature.users.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
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
import uz.msit.demo.feature.users.MainCoroutineRule
import uz.msit.demo.feature.users.domain.failure.GetUsersFailure
import uz.msit.demo.feature.users.domain.repository.UserRepository
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
        given(mockRepository.getUserDetails(userDetails.login)).willReturn(Result.success(userDetails))

        val response = sut(userDetails.login)

        Mockito.verify(mockRepository, Mockito.times(1)).getUserDetails(userDetails.login)
        assertEquals(userDetails, response.getOrNull())
    }

    @Test
    fun `when failure should return failure`() = runTest {
        val expected = GetUsersFailure.UnknownError
        given(mockRepository.getUserDetails(userDetails.login)).willReturn(Result.failure(expected))

        val response =  sut(userDetails.login)

        Mockito.verify(mockRepository, Mockito.times(1)).getUserDetails(userDetails.login)
        assertEquals(expected, response.exceptionOrNull())
    }
}