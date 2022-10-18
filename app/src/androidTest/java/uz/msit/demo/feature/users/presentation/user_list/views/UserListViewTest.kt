package uz.msit.demo.feature.users.presentation.user_list.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.msit.demo.feature.users.domain.model.User
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags

@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class UserListViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun did_user_list_display_users() {
        composeTestRule.apply {
            val testItems = listOf(User(1, "sasa", "vava"), User(2, "vava", "dada"))
            val expectedUser = testItems.first()
            var clickedItem: User? = null

            setContent { UserListView(testItems, onItemClick = { clickedItem = it }) }

            onNodeWithTag(Tags.USER_LIST_VIEW)
                .onChildren()
                .assertCountEquals(testItems.size)
                .onFirst()
                .assert(hasText(expectedUser.login)).performClick()

            assertEquals(expectedUser, clickedItem)
        }
    }
}