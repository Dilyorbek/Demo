package uz.msit.demo.users.presentation.user_details.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.msit.demo.users.domain.model.User

@RunWith(AndroidJUnit4::class)
class UserDetailsItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testItem = Pair("public_repos", "64")

    @Before
    fun setUp() {
        composeTestRule.setContent { UserDetailsItemView(testItem) }
    }

    @Test
    fun did_display_key_and_value() {
        composeTestRule.onNodeWithText(testItem.first).assertIsDisplayed()
        composeTestRule.onNodeWithText(testItem.second).assertIsDisplayed()
    }
}