package uz.msit.demo.feature.users.presentation.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.filters.SmallTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags

@SmallTest
class ErrorViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val message = "Unknown error!"
    private val buttonText = "Retry"
    private var isButtonClickCalled = false

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ErrorView(
                message = message,
                buttonText = buttonText,
                onButtonClick = { isButtonClickCalled = true }
            )
        }
    }

    @Test
    fun did_display_correct_message_and_button_text() {
        composeTestRule.apply {
            onNodeWithTag(Tags.ERROR_MESSAGE).assertIsDisplayed()
            onNodeWithTag(Tags.ERROR_BUTTON).assertIsDisplayed()
            onNodeWithText(message).assertIsDisplayed()
            onNodeWithText(buttonText).assertIsDisplayed()
        }
    }

    @Test
    fun did_display_correct_message_and_trick_callback_action() {
        composeTestRule.onNodeWithText(buttonText).performClick()

        assert(isButtonClickCalled)
    }
}