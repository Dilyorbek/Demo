package uz.msit.demo.users.presentation.user_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserListScreen(
    viewModel: UsersViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val users = state.users

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(users.size) { i ->
            val user = users[i]
            UserItem(
                user = user,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
//                                navigator.navigate(
//                                    CompanyInfoScreenDestination(company.symbol)
//                                )
                    }
                    .padding(16.dp)
            )
            if (i < users.size) {
                Divider(
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    )
                )
            }

        }
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}


@Preview
@Composable
fun UserListScreenPreview() {
    UserListScreen()
}

