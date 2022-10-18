package uz.msit.demo.feature.users.data.remote.data_source


import junit.framework.TestCase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.msit.demo.feature.users.MainCoroutineRule
import uz.msit.demo.feature.users.data.remote.service.UserService
import uz.msit.demo.utils.TestData

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

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `did getUsers has correct endpoint`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestData.testUsersJson)
                .setResponseCode(200)
        )
        userService.getUsers()

        assertEquals("/users", mockWebServer.takeRequest().path)
    }

    @Test
    fun `did getUsers on success return correct list`() = runTest {
        val expected = TestData.testUsers

        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestData.testUsersJson)
                .setResponseCode(200)
        )

        val response = userService.getUsers()

        assertTrue(response.isSuccessful)
        assertEquals(response.body(), expected)
    }

    @Test
    fun `did getUsers on failure response unsuccessful`() = runTest {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(404)
        )

        val response = userService.getUsers()
        assertFalse(response.isSuccessful)
    }

    @Test
    fun `did getUserDetails has correct endpoint`() = runTest {
        val expected = TestData.testUserDetails
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestData.testUserDetailsJson)
                .setResponseCode(200)
        )

        userService.getUserDetails(expected.login)

        assertEquals("/users/technoweenie", mockWebServer.takeRequest().path)
    }

    @Test
    fun `did getUserDetails on success return correct details`() = runTest {
        val expected = TestData.testUserDetails
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestData.testUserDetailsJson)
                .setResponseCode(200)
        )

        val response = userService.getUserDetails(expected.login)

        assertTrue(response.isSuccessful)
        assertEquals(expected, response.body())
    }

    @Test
    fun `did getUserDetails  on failure response unsuccessful`() = runTest {
        val expected = TestData.testUserDetails
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        val response = userService.getUserDetails(expected.login)

        assertFalse(response.isSuccessful)
    }
}