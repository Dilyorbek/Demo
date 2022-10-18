package uz.msit.demo.feature.users.presentation.user_list.views

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import uz.msit.demo.feature.users.domain.model.User
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags
import uz.msit.demo.feature.users.presentation.preview_param_provider.UserProvider

@Composable
fun UserItemView(
    user: User,
    isPlaceholderVisible: Boolean = false,
    modifier: Modifier = Modifier.testTag(Tags.USER_ITEM_VIEW)
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.avatar_url,
            contentDescription = Tags.USER_IMAGE,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onBackground,
                    shape = CircleShape
                )
                .placeholder(
                    visible = isPlaceholderVisible,
                    shape = MaterialTheme.shapes.small,
                    highlight = PlaceholderHighlight.shimmer(),
                )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = user.login,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = MaterialTheme.colors.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .weight(1f)
                .placeholder(
                    visible = isPlaceholderVisible,
                    shape = MaterialTheme.shapes.small,
                    highlight = PlaceholderHighlight.shimmer(),
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemViewPreview(@PreviewParameter(UserProvider::class) user: User) {
    UserItemView(user = user, true)
}