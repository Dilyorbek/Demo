package uz.msit.demo.users.presentation.user_list.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import timber.log.Timber
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.presentation.preview_param_provider.UserProvider

@Composable
fun UserListView(
    users: List<User> = emptyList(),
    onItemClick: (user: User) -> Unit,
    isPlaceholderVisible: Boolean = false,
    modifier: Modifier = Modifier.testTag("UserListView")
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(users.size) { i ->
            val user = users[i]
            UserItemView(
                user = user,
                isPlaceholderVisible = isPlaceholderVisible,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(user)
                    }
                    .padding(16.dp)
            )
            if (i < users.size) {
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@Preview
@Composable
fun UserListViewPreview(@PreviewParameter(UserProvider::class) users: List<User>) {
    UserListView(users, {
        Timber.d("Item:${it.login}")
    })
}