package uz.msit.demo.feature.users.presentation.user_details.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.SmallTest
import org.junit.Rule
import org.junit.Test
import uz.msit.demo.feature.users.domain.model.UserDetails
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags

@SmallTest
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

    @Test
    fun did_display_details() {
        composeTestRule.apply {
            setContent { UserDetailsListView(testUserDetails) }
            onNodeWithTag(Tags.USER_DETAILS_HEADER_VIEW).assertIsDisplayed()
            onAllNodesWithTag(Tags.USER_DETAILS_ITEM_VIEW).assertCountEquals(4)
            onNodeWithText("Repos").assertIsDisplayed()
            onNodeWithText("Gists").assertIsDisplayed()
            onNodeWithText("Followers").assertIsDisplayed()
            onNodeWithText("64").assertIsDisplayed()
            onNodeWithText("62").assertIsDisplayed()
            onNodeWithText("23194").assertIsDisplayed()
            onNodeWithText("11").assertIsDisplayed()
        }
    }
}