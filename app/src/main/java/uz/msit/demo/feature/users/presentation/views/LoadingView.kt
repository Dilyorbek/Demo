package uz.msit.demo.feature.users.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags

@Composable
fun LoadingView(
    modifier: Modifier = Modifier.testTag(Tags.LOADING_VIEW)
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.testTag(Tags.LOADING_INDICATOR_VIEW))
    }
}

@Preview
@Composable
fun LoadingViewPreview() {
    LoadingView()
}