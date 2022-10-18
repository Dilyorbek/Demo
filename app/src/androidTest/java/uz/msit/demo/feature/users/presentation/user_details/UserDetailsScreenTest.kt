package uz.msit.demo.feature.users.presentation.user_details

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.SavedStateHandle
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import uz.msit.demo.feature.users.domain.model.UserDetails
import uz.msit.demo.feature.users.domain.usecase.GetUserDetailsUseCase
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags

class UserDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var getUserDetailsUseCase: GetUserDetailsUseCase
    private lateinit var fakeUserDetailsViewModel: FakeUserDetailsViewModel

    private val testUserDetails = UserDetails(
        1,
        "mojombo",
        "https://avatars.githubusercontent.com/u/1?v=4",
        64,
        62,
        23194,
        11
    )

    private var isNavigatedToUpCalled: Boolean = false

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        fakeUserDetailsViewModel = FakeUserDetailsViewModel(
            getUserDetailsUseCase,
            SavedStateHandle().apply { set("login", testUserDetails.login) }
        )
        composeTestRule.setContent {
            UserDetailsScreen(
                { isNavigatedToUpCalled = true },
                fakeUserDetailsViewModel)
        }
    }


    @Test
    fun did_users_will_displayed() {
        fakeUserDetailsViewModel.setState(UserDetailsState(testUserDetails))

        composeTestRule.onNodeWithTag(Tags.USER_DETAILS_LIST_VIEW).assertIsDisplayed()
    }

    @Test
    fun did_loading_will_displayed() {
        fakeUserDetailsViewModel.setState(UserDetailsState(isLoading = true))

        composeTestRule.onNodeWithTag(Tags.LOADING_INDICATOR_VIEW).assertIsDisplayed()
    }

    @Test
    fun did_error_will_displayed() {
        fakeUserDetailsViewModel.setState(UserDetailsState(message = "Error message"))

        composeTestRule.onNodeWithTag(Tags.ERROR_VIEW).assertIsDisplayed()
    }

    @Test
    fun did_back_icon_will_call_navigation_callback() {
        fakeUserDetailsViewModel.setState(UserDetailsState())

        composeTestRule.onNodeWithTag(Tags.TOP_BAR)
        composeTestRule.onNodeWithContentDescription(Tags.BACK_BUTTON).performClick()

        assert(isNavigatedToUpCalled)
    }

    @Test
    fun did_retry_will_call_on_event() {
        val expectedEvent = UserDetailsEvent.GetUserDetails
        fakeUserDetailsViewModel.setState(UserDetailsState(message = "Error message"))

        composeTestRule.onNodeWithTag(Tags.ERROR_BUTTON).performClick()

        assertEquals(expectedEvent, fakeUserDetailsViewModel.lastEvent)
    }
}