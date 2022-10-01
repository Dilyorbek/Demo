package uz.msit.demo.users.presentation.user_list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber
import uz.msit.demo.users.domain.model.User

@Composable
fun UserList(
    users: List<User> = emptyList(),
    onItemClick: (user: User) -> Unit,
    modifier: Modifier = Modifier.testTag("UserList")
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(users.size) { i ->
            val user = users[i]
            UserItem(
                user = user,
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
fun UserListPreview() {
    val users = listOf(User(1, "sasa", "vava"), User(2, "vava", "dada"))
    UserList(users, {
        Timber.d("Item:${it.username}")
    })
}