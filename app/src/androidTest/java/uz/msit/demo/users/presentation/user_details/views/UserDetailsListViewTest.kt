package uz.msit.demo.users.presentation.user_details.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.msit.demo.users.domain.model.User

class UserDetailsListViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testItems = listOf(
        Pair("Repos", "64"),
        Pair("Gists", "62"),
        Pair("Followers", "1233"),
        Pair("Following", "11")
    )

    @Before
    fun setup() {
        composeTestRule.setContent { UserDetailsListView(testItems) }
    }

    @Test
    fun did_display_details() {
        composeTestRule.onNodeWithTag("UserDetailsListView")
            .onChildren()
            .assertCountEquals(testItems.size)
    }
}