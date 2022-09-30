package uz.msit.demo.users.data.remote.data_source

import junit.framework.Assert
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.given
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @get:Rule
    val mockWebServer = MockWebServer()

    private val userService by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

    private lateinit var sut: UserRemoteDataSourceImpl
    private val testJson = """
            [
                {"id": 1, "login": "mojombo", "avatar_url": "https://avatars.githubusercontent.com/u/1?v=4"},
                {"id": 3, "login": "pjhyett", "avatar_url": "https://avatars.githubusercontent.com/u/3?v=4"}
            ]
            """

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `when response success should return correct user list`() = runTest {
        sut = UserRemoteDataSourceImpl(userService)

        val users = listOf(
            User(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
            User(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4")
        )
        mockWebServer.enqueue(
            MockResponse()
                .setBody(testJson)
                .setResponseCode(200)
        )

        val response = sut.getUsers()

        assertEquals(response, users)
    }



    @Test
    fun `when request throw exception should return failure`() = runTest {
        sut = UserRemoteDataSourceImpl(userService)
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(400)
        )
        val exception = GetUsersFailure.UnknownError

        val response =  runCatching {  sut.getUsers() }

        assertEquals(response.exceptionOrNull(), exception)
    }

    @Test
    fun `when throw ioException should throw correct exception`() = runTest {
        val mockService = Mockito.mock(UserService::class.java)

        sut = UserRemoteDataSourceImpl(mockService)

        given(mockService.getUsers()).willAnswer { throw IOException() }

        val exception = GetUsersFailure.NoNetworkConnection

        val response =  runCatching {  sut.getUsers() }

        assertEquals(response.exceptionOrNull(), exception)
    }
}