package uz.msit.demo.feature.users.presentation.user_details.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.msit.demo.feature.users.domain.model.UserDetails
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags
import uz.msit.demo.feature.users.presentation.preview_param_provider.UserDetailsProvider

@Composable
fun UserDetailsHeaderView(
    login: String,
    userPhoto: String,
    modifier: Modifier = Modifier.testTag(Tags.USER_DETAILS_HEADER_VIEW)
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(
            model = userPhoto,
            contentDescription = Tags.USER_IMAGE,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onBackground,
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = login,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = MaterialTheme.colors.primary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun UserDetailsHeaderViewPreview(@PreviewParameter(UserDetailsProvider::class) user: UserDetails) {
    UserDetailsHeaderView( user.login, user.avatar_url)
}