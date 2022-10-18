package uz.msit.demo.feature.users.presentation.user_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.filters.SmallTest
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import uz.msit.demo.feature.users.domain.model.User
import uz.msit.demo.feature.users.domain.usecase.GetUsersUseCase
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags

@SmallTest
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

        composeTestRule.onNodeWithTag(Tags.USER_LIST_VIEW).assertIsDisplayed()
    }

    @Test
    @Ignore
    fun did_loading_will_displayed() {
        fakeUsersViewModel.setState(UsersState(isLoading = true))

        composeTestRule.onNodeWithTag(Tags.LOADING_INDICATOR_VIEW).assertIsDisplayed()
    }

    @Test
    fun did_error_will_displayed() {
        fakeUsersViewModel.setState(UsersState(message = "Error message"))

        composeTestRule.onNodeWithTag(Tags.ERROR_VIEW).assertIsDisplayed()
    }

    @Test
    fun did_item_click_will_call_navigation_callback() {
        val expectedUser = testUsers.first().login
        fakeUsersViewModel.setState(UsersState(testUsers))

        composeTestRule.onNodeWithText(expectedUser).performClick()

        assertEquals(expectedUser, navigatedUserId)
    }

    @Test
    fun did_retry_will_call_on_event() {
        val expectedEvent = UsersEvent.GetAll
        fakeUsersViewModel.setState(UsersState(message = "Error message"))

        composeTestRule.onNodeWithTag(Tags.ERROR_BUTTON).performClick()

        assertEquals(expectedEvent, fakeUsersViewModel.lastEvent)
    }
}