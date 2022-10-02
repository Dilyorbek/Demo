package uz.msit.demo.users.presentation.user_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.usecase.GetUsersUseCase

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UsersScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var getUsersUseCase: GetUsersUseCase
    private lateinit var fakeUsersViewModel: FakeUsersViewModel

    private val testUsers = listOf(User(1, "sasa", "vava"))
    private var navigatedUserId: String? = null

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        fakeUsersViewModel = FakeUsersViewModel(getUsersUseCase)
        composeTestRule.setContent { UsersScreen({ navigatedUserId = it }, fakeUsersViewModel) }
    }


    @Test
    fun did_users_will_displayed() {
        fakeUsersViewModel.setState(UsersState(testUsers))

        composeTestRule.onNodeWithTag("UserList").assertIsDisplayed()
    }

    @Test
    fun did_loading_will_displayed() {
        fakeUsersViewModel.setState(UsersState(isLoading = true))

        composeTestRule.onNodeWithTag("CircularProgressIndicator").assertIsDisplayed()
    }

    @Test
    fun did_error_will_displayed() {
        fakeUsersViewModel.setState(UsersState(message = "Error message"))

        composeTestRule.onNodeWithTag("Error").assertIsDisplayed()
    }

    @Test
    fun did_navigation_will_called() {
        val expectedUser = testUsers.first().login
        fakeUsersViewModel.setState(UsersState(testUsers))

        composeTestRule.onNodeWithText(expectedUser).performClick()

        assertEquals(expectedUser, navigatedUserId)
    }

    @Test
    fun did_retry_will_call_on_event() {
        val expectedEvent = UsersEvent.GetAll
        fakeUsersViewModel.setState(UsersState(message = "Error message"))

        composeTestRule.onNodeWithText("Retry").performClick()

        assertEquals(expectedEvent, fakeUsersViewModel.lastEvent)
    }
}