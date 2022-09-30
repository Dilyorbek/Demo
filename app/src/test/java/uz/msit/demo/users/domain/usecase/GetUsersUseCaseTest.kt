package uz.msit.demo.users.domain.usecase

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
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
import uz.msit.demo.users.domain.repository.UserRepository

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
internal class GetUsersUseCaseTest {

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
        given(mockRepository.getUsers()).willReturn(flow { emit(Right(users)) })

        val response = sut().last().right()

        Mockito.verify(mockRepository, Mockito.times(1)).getUsers()
        assertEquals(response, users)
    }

    @Test
    fun `when failure should return failure`() = runTest {
        val failure = GetUsersFailure.UnknownError
        given(mockRepository.getUsers()).willReturn(flow { emit(Left(failure)) })

        val response = sut().last().left()

        Mockito.verify(mockRepository, Mockito.times(1)).getUsers()
        assertEquals(response, failure)
    }
}