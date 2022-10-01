package uz.msit.demo.users.presentation.user_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import timber.log.Timber
import uz.msit.demo.users.presentation.components.Error
import uz.msit.demo.users.presentation.components.Loading

@Composable
fun UsersScreen(
    navigateToDetails: (userId: String) -> Unit,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        if (state.users.isNotEmpty()) {
            UserList(state.users, onItemClick = {
                navigateToDetails(it.username)
            })
        } else if (state.isLoading) {
            Loading()
        } else if (!state.message.isNullOrEmpty()){
            Error(
                state.message!!,
                "Retry",
                onButtonClick = {
                    viewModel.onEvent(UsersEvent.GetAll)
                }
            )
        }
    }
}

@Preview
@Composable
fun UserListScreenPreview() {
    UsersScreen({
        Timber.d("navigateToDetails:${it}")
    })
}

