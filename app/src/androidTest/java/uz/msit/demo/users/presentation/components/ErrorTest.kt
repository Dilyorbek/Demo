package uz.msit.demo.users.presentation.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.presentation.user_list.UserItem
import uz.msit.demo.users.presentation.user_list.UserList

class ErrorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val message = "Unknown error!"
    private val buttonText = "Retry"
    var isButtonClickCalled = false

    @Before
    fun setUp() {
        composeTestRule.setContent { Error(message = message, buttonText, { isButtonClickCalled = true }) }
    }

    @Test
    fun did_display_correct_message_and_button_text() {
        composeTestRule.onNodeWithText(buttonText).assertIsDisplayed()
        composeTestRule.onNodeWithText(message).assertIsDisplayed()
    }

    @Test
    fun did_display_correct_message_and_trick_callback_action() {
        composeTestRule.onNodeWithText(buttonText).performClick()

        assert(isButtonClickCalled)
    }
}