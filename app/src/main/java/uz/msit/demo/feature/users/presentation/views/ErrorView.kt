package uz.msit.demo.feature.users.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import timber.log.Timber
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags

@Composable
fun ErrorView(
    message: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier.testTag(Tags.ERROR_VIEW)
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colors.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.testTag(Tags.ERROR_MESSAGE)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onButtonClick,
                modifier = Modifier.testTag(Tags.ERROR_BUTTON)
            ) {
                Text(text = buttonText)
            }
        }
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(
        "Unknown  error!",
        "Retry",
        onButtonClick = {
        Timber.d("OK clicked!")
    })
}