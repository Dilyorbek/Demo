package uz.msit.demo.users.presentation.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.presentation.user_list.UserItem
import uz.msit.demo.users.presentation.user_list.UserList

class LoadingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent { Loading() }
    }

    @Test
    fun did_contains_circular_progress() {
        composeTestRule.onNodeWithTag("CircularProgressIndicator").assertIsDisplayed()
    }

}