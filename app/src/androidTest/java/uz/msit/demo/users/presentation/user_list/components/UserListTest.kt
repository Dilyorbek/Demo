package uz.msit.demo.users.presentation.user_list.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.presentation.user_list.UserList

class UserListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testUsers = listOf(User(1, "sasa", "vava"), User(2, "vava", "dada"))
    private var clickedItem: User? = null

    @Before
    fun setup() {
        composeTestRule.setContent { UserList(testUsers, onItemClick = { clickedItem = it }) }
    }

    @Test
    fun did_user_list_display_users() {
        val expectedUser = testUsers.first()

        composeTestRule.onNodeWithTag("UserList")
            .onChildren()
            .assertCountEquals(testUsers.size)
            .onFirst()
            .assert(hasText(expectedUser.login)).performClick()

        assertEquals(expectedUser, clickedItem)
    }

}