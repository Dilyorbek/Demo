package uz.msit.demo.users.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import uz.msit.demo.core.utils.left
import uz.msit.demo.core.utils.right
import uz.msit.demo.users.MainCoroutineRule
import uz.msit.demo.users.data.remote.data_source.UserRemoteDataSource
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.utils.TestData


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
internal class UserRepositoryImplTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
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
    fun `did getUsers on success return right`() = runTest {
        val expected = TestData.testUsers
        given(mockRemoteDataSource.getUsers()).willReturn(expected)

        val response = sut.getUsers().last().right()

        Mockito.verify(mockRemoteDataSource, Mockito.times(1)).getUsers()
        assertEquals(expected, response)
    }

    @Test
    fun `did getUsers on failure return left`() = runTest {
        val expected = GetUsersFailure.UnknownError
        given(mockRemoteDataSource.getUsers()).willThrow(expected)

        val response = sut.getUsers().last().left()

        Mockito.verify(mockRemoteDataSource, Mockito.times(1)).getUsers()
        assertEquals(expected, response)
    }

    @Test
    fun `did getUserDetails on success return right`() = runTest {
        val expected = TestData.testUserDetails
        given(mockRemoteDataSource.getUserDetails(any())).willReturn(expected)

        val response = sut.getUserDetails(expected.login).last().right()

        Mockito.verify(mockRemoteDataSource, Mockito.times(1))
            .getUserDetails(expected.login)
        assertEquals(expected, response)
    }

    @Test
    fun `did getUserDetails on failure return left`() = runTest {
        val expected = GetUsersFailure.UnknownError
        val login = "login"
        given(mockRemoteDataSource.getUserDetails(any())).willThrow(expected)

        val response = sut.getUserDetails(login).last().left()

        Mockito.verify(mockRemoteDataSource, Mockito.times(1))
            .getUserDetails(login)
        assertEquals(expected, response)
    }
}