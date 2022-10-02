package uz.msit.demo.users.presentation.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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