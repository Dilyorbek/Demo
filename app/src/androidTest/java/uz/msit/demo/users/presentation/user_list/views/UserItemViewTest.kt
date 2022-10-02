package uz.msit.demo.users.presentation.user_list.views

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.msit.demo.users.domain.model.User

class UserItemViewTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val testItem = User(1, "sasa", "vava")

    @Before
    fun setUp() {
        composeTestRule.setContent { UserItemView(user = testItem) }
    }

    @Test
    fun did_display_username_and_photo() {
        composeTestRule.onNodeWithText(testItem.login).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("user_photo").assertIsDisplayed()
    }
}