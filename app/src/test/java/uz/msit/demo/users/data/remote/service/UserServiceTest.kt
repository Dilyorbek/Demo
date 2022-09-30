package uz.msit.demo.users.data.remote.data_source


import junit.framework.TestCase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.msit.demo.users.MainCoroutineRule
import uz.msit.demo.users.data.remote.service.UserService
import uz.msit.demo.users.domain.model.User

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
internal class UserServiceTest {

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
    fun `get users has correct endpoint`() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setBody(testJson)
                .setResponseCode(200)
        )

        val response = userService.getUsers()
        assertTrue(response.isSuccessful)

        assertEquals("/users", mockWebServer.takeRequest().path)
    }

    @Test
    fun `get users when response code 200`() = runTest {
        val userList = listOf(
            User(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
            User(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4")
        )

        mockWebServer.enqueue(
            MockResponse()
                .setBody(testJson)
                .setResponseCode(200)
        )

        val response = userService.getUsers()
        assertTrue(response.isSuccessful)

        assertEquals(response.body(), userList)
    }

    @Test
    fun `get users when response code not 200`() = runTest {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(404)
        )

        val response = userService.getUsers()
        assertFalse(response.isSuccessful)
    }
}