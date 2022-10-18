package uz.msit.demo.feature.users.presentation.user_details.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import uz.msit.demo.feature.users.domain.model.UserDetails
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags
import uz.msit.demo.feature.users.presentation.preview_param_provider.UserDetailsProvider

@Composable
fun UserDetailsListView(
    userDetails: UserDetails,
    modifier: Modifier = Modifier.testTag(Tags.USER_DETAILS_LIST_VIEW)
) {
    val items = listOf(
        Pair("Repos", userDetails.public_repos.toString()),
        Pair("Gists", userDetails.public_gists.toString()),
        Pair("Followers", userDetails.followers.toString()),
        Pair("Following", userDetails.following.toString())
    )

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
        items(items.size + 1) { i ->
            if (i == 0) {
                UserDetailsHeaderView(
                    userDetails.login,
                    userDetails.avatar_url
                )
            } else {
                UserDetailsItemView(
                    item = items[i - 1],
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .testTag(Tags.USER_DETAILS_ITEM_VIEW)
                )
            }
            if (i <= items.size) {
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@Preview
@Composable
fun UserDetailsListViewPreview(@PreviewParameter(UserDetailsProvider::class) userDetails: UserDetails) {
    UserDetailsListView(userDetails = userDetails)
}