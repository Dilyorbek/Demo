package uz.msit.demo.users.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingView(
    modifier: Modifier = Modifier.testTag("Loading")
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.testTag("CircularProgressIndicator"))
    }
}

@Preview
@Composable
fun LoadingViewPreview() {
    LoadingView()
}