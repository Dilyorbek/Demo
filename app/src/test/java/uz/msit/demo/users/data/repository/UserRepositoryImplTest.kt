package uz.msit.demo.users.data.repository

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import uz.msit.demo.core.utils.left
import uz.msit.demo.core.utils.right
import uz.msit.demo.users.MainCoroutineRule
import uz.msit.demo.users.data.remote.data_source.UserRemoteDataSource
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.User


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
internal class UserRepositoryImplTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mockRemoteDataSource: UserRemoteDataSource
    private lateinit var sut: UserRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sut = UserRepositoryImpl(mockRemoteDataSource)
    }

    @Test
    fun `when request success should return correct users`() = runTest {
        val users = listOf(User(1, "sasa", "vava"), User(2, "vava", "dada"))
        given(mockRemoteDataSource.getUsers()).willReturn(users)

        val response = sut.getUsers().last().right()

        Mockito.verify(mockRemoteDataSource, Mockito.times(1)).getUsers()
        assertEquals(response, users)
    }

    @Test
    fun `when request throw exception should return failure`() = runTest {
        val exception = GetUsersFailure.UnknownError
        given(mockRemoteDataSource.getUsers()).willThrow(exception)

        val response = sut.getUsers().last().left()

        Mockito.verify(mockRemoteDataSource, Mockito.times(1)).getUsers()
        assertEquals(response, exception)
    }
}