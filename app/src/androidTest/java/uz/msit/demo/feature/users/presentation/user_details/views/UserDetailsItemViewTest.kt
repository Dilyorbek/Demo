package uz.msit.demo.feature.users.presentation.user_details.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.SmallTest
import org.junit.Rule
import org.junit.Test

@SmallTest
class UserDetailsItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun did_display_key_and_value() {
        composeTestRule.apply {
            val testItem = Pair("public_repos", "64")

            setContent { UserDetailsItemView(testItem) }

            onNodeWithText(testItem.first).assertIsDisplayed()
            onNodeWithText(testItem.second).assertIsDisplayed()
        }
    }
}