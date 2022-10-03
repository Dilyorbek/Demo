package uz.msit.demo.users.presentation.user_details.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.model.UserDetails
import uz.msit.demo.users.presentation.preview_param_provider.UserDetailsProvider

class UserDetailsListViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testUserDetails = UserDetails(
        1,
        "mojombo",
        "https://avatars.githubusercontent.com/u/1?v=4",
        64,
        62,
        23194,
        11
    )


    @Before
    fun setup() {
        composeTestRule.setContent { UserDetailsListView(testUserDetails) }
    }

    @Test
    fun did_display_details() {
        composeTestRule.onNodeWithTag("UserDetailsHeaderView").assertIsDisplayed()
        composeTestRule.onNodeWithTag("UserDetailsItemView")
            .onChildren()
            .assertCountEquals(4)

        composeTestRule.onNodeWithTag("Repos").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Repos").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Gists").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Followers").assertIsDisplayed()
        composeTestRule.onNodeWithTag("64").assertIsDisplayed()
        composeTestRule.onNodeWithTag("62").assertIsDisplayed()
        composeTestRule.onNodeWithTag("23194").assertIsDisplayed()
        composeTestRule.onNodeWithTag("11").assertIsDisplayed()
    }
}