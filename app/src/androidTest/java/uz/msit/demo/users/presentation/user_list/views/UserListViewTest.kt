package uz.msit.demo.users.presentation.user_list.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.msit.demo.users.domain.model.User

class UserListViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testItems = listOf(User(1, "sasa", "vava"), User(2, "vava", "dada"))
    private var clickedItem: User? = null

    @Before
    fun setup() {
        composeTestRule.setContent { UserListView(testItems, onItemClick = { clickedItem = it }) }
    }

    @Test
    fun did_user_list_display_users() {
        val expectedUser = testItems.first()

        composeTestRule.onNodeWithTag("UserListView")
            .onChildren()
            .assertCountEquals(testItems.size)
            .onFirst()
            .assert(hasText(expectedUser.login)).performClick()

        assertEquals(expectedUser, clickedItem)
    }

}