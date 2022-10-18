package uz.msit.demo.feature.users.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
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
import uz.msit.demo.core.utils.*
import uz.msit.demo.feature.users.MainCoroutineRule
import uz.msit.demo.feature.users.domain.failure.GetUsersFailure
import uz.msit.demo.feature.users.domain.model.User
import uz.msit.demo.feature.users.domain.repository.UserRepository

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
internal class GetUsersUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockRepository: UserRepository
    private lateinit var sut: GetUsersUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sut = GetUsersUseCase(mockRepository)
    }

    @Test
    fun `when success should return correct users`() = runTest {
        val users = listOf(User(1, "sasa", "vava"), User(2, "vava", "dada"))
        given(mockRepository.getUsers()).willReturn(Result.success(users))

        val response = sut(NoParameters)

        Mockito.verify(mockRepository, Mockito.times(1)).getUsers()
        assertEquals(users, response.getOrNull())
    }

    @Test
    fun `when failure should return failure`() = runTest {
        val failure = GetUsersFailure.UnknownError
        given(mockRepository.getUsers()).willReturn(Result.failure(failure))

        val response = sut(NoParameters)

        Mockito.verify(mockRepository, Mockito.times(1)).getUsers()
        assertEquals(failure, response.exceptionOrNull())
    }
}