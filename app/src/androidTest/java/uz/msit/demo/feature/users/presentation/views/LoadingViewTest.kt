package uz.msit.demo.feature.users.presentation.views

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.SmallTest
import org.junit.Rule
import org.junit.Test
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags

@SmallTest
class LoadingViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun did_contains_circular_progress() {
        composeTestRule.apply {
            setContent { LoadingView() }
            onNodeWithTag(Tags.LOADING_INDICATOR_VIEW).assertIsDisplayed()
        }
    }
}