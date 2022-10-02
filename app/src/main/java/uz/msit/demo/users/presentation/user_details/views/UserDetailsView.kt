package uz.msit.demo.users.presentation.user_details.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uz.msit.demo.users.domain.model.UserDetails
import uz.msit.demo.users.presentation.preview_param_provider.UserDetailsProvider
import uz.msit.demo.users.presentation.user_list.views.UserItemView

@Composable
fun UserDetailsView(
    userDetails: UserDetails,
    modifier: Modifier = Modifier.testTag("UserDetailsView")
) {
    val items = listOf(
        Pair("Repos", userDetails.public_repos.toString()),
        Pair("Gists", userDetails.public_gists.toString()),
        Pair("Followers", userDetails.followers.toString()),
        Pair("Following", userDetails.following.toString())
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(
            model = userDetails.avatar_url,
            contentDescription = "user_photo",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onBackground,
                    shape = CircleShape
                )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = userDetails.login,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = MaterialTheme.colors.primary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(modifier = Modifier.padding(horizontal = 16.dp))
        UserDetailsListView(items)
    }
}

@Preview
@Composable
fun UserDetailsViewPreview(@PreviewParameter(UserDetailsProvider::class) user: UserDetails) {
    UserDetailsView(userDetails = user)
}