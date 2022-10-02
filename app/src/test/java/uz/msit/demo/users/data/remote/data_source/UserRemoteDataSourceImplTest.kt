package uz.msit.demo.users.data.remote.data_source

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import retrofit2.Response
import uz.msit.demo.users.MainCoroutineRule
import uz.msit.demo.users.data.remote.service.UserService
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.model.UserDetails
import uz.msit.demo.utils.TestData
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
internal class UserRemoteDataSourceImplTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    @Mock
    private lateinit var mockService: UserService
    private lateinit var sut: UserRemoteDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sut = UserRemoteDataSourceImpl(mockService)
    }

    @Test
    fun `did getUsers on success return correct list`() = runTest {
        val expected = TestData.testUsers
        given(mockService.getUsers()).willAnswer { Response.success(200, expected) }

        val response = sut.getUsers()

        assertEquals(expected, response)
    }

    @Test
    fun `did getUsers on failure will throw exception`() = runTest {
        val exception = GetUsersFailure.UnknownError
        given(mockService.getUsers()).willAnswer { (Response.error<List<User>>(500, ResponseBody.create(null, ""))) }

        val response = runCatching { sut.getUsers() }

        assertEquals(exception, response.exceptionOrNull())
    }

    @Test
    fun `did getUsers on ioException will throw exception`() = runTest {
        val exception = GetUsersFailure.NoNetworkConnection
        given(mockService.getUsers()).willAnswer { throw IOException() }

        val response = runCatching { sut.getUsers() }

        assertEquals(exception, response.exceptionOrNull())
    }

    @Test
    fun `did getUserDetails on success return correct details`() = runTest {
        val expected = TestData.testUserDetails
        given(mockService.getUserDetails(expected.login))
            .willAnswer { Response.success(200, expected) }

        val response = sut.getUserDetails(expected.login)

        assertEquals(expected, response)
    }

    @Test
    fun `did getUserDetails on failure will throw exception`() = runTest {
        val exception = GetUsersFailure.UnknownError
        val userDetails = TestData.testUserDetails
        given(mockService.getUserDetails(userDetails.login))
            .willAnswer {
                (Response.error<UserDetails>(500, ResponseBody.create(null, "")))
            }

        val response = runCatching { sut.getUserDetails(userDetails.login) }

        assertEquals(exception, response.exceptionOrNull())
    }

    @Test
    fun `did getUserDetails on ioException will throw exception`() = runTest {
        val exception = GetUsersFailure.NoNetworkConnection
        val userDetails = TestData.testUserDetails
        given(mockService.getUserDetails(userDetails.login)).willAnswer { throw IOException() }

        val response = runCatching { sut.getUserDetails(userDetails.login) }

        assertEquals(exception, response.exceptionOrNull())
    }
}