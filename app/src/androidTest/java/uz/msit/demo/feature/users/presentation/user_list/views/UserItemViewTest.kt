package uz.msit.demo.feature.users.presentation.user_list.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.SmallTest
import org.junit.Rule
import org.junit.Test
import uz.msit.demo.feature.users.domain.model.User
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags

@SmallTest
class UserItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun did_display_username_and_photo() {
        composeTestRule.apply {
            val testItem = User(1, "sasa", "vava")
            setContent { UserItemView(user = testItem) }
            onNodeWithText(testItem.login).assertIsDisplayed()
            onNodeWithContentDescription(Tags.USER_IMAGE).assertIsDisplayed()
        }
    }
}