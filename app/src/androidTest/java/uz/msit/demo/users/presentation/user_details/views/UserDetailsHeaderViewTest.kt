package uz.msit.demo.users.presentation.user_details.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.msit.demo.users.domain.model.User
import kotlin.math.log

@RunWith(AndroidJUnit4::class)
class UserDetailsHeaderViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val login = "mojombo"
    private val userPhoto = "https://avatars.githubusercontent.com/u/1?v=4"

    @Before
    fun setUp() {
        composeTestRule.setContent { UserDetailsHeaderView(login, userPhoto) }
    }

    @Test
    fun did_display_key_and_value() {
        composeTestRule.onNodeWithText(login).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("user_photo").assertIsDisplayed()
    }
}