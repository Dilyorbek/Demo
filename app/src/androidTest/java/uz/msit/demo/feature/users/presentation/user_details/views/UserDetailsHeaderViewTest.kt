package uz.msit.demo.feature.users.presentation.user_details.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.SmallTest
import org.junit.Rule
import org.junit.Test
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags

@SmallTest
class UserDetailsHeaderViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun did_display_login_and_image() {
        composeTestRule.apply {
            val login = "mojombo"
            val userPhoto = "https://avatars.githubusercontent.com/u/1?v=4"

            setContent { UserDetailsHeaderView(login, userPhoto) }
            onNodeWithText(login).assertIsDisplayed()
            onNodeWithContentDescription(Tags.USER_IMAGE).assertIsDisplayed()
        }
    }
}