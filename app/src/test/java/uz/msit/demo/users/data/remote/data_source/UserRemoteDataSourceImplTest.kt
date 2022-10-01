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
    fun `when response success should return correct user list_123`() = runTest {
        val expected = listOf(
            User(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
            User(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4")
        )
        given(mockService.getUsers()).willAnswer{Response.success(200, expected)}

        val response = sut.getUsers()

        assertEquals(expected, response)
    }

    @Test
    fun `when request throw exception should return failure`() = runTest {
        val exception = GetUsersFailure.UnknownError
        given(mockService.getUsers()).willAnswer { (Response.error<Exception>(500, ResponseBody.create(null,  ""))) }

        val response = runCatching { sut.getUsers() }

        assertEquals(exception, response.exceptionOrNull())
    }

    @Test
    fun `when throw ioException should throw correct exception`() = runTest {
        val exception = GetUsersFailure.NoNetworkConnection
        given(mockService.getUsers()).willAnswer { throw IOException() }

        val response = runCatching { sut.getUsers() }

        assertEquals(response.exceptionOrNull(), exception)
    }
}