package uz.msit.demo.users.presentation.user_list

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import uz.msit.demo.users.presentation.views.ErrorView
import uz.msit.demo.users.presentation.views.LoadingView
import uz.msit.demo.users.presentation.user_list.views.UserListView

@Composable
fun UsersScreen(
    navigateToDetails: (userId: String) -> Unit,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Users") })
        },
        scaffoldState = scaffoldState
    ) {
        if (state.data.isNotEmpty()) {
            UserListView(state.data, onItemClick = {
                navigateToDetails(it.login)
            })
        } else if (state.isLoading) {
            LoadingView()
        } else if (!state.message.isNullOrEmpty()){
            ErrorView(
                state.message!!,
                "Retry",
                onButtonClick = {
                    viewModel.onEvent(UsersEvent.GetAll)
                }
            )
        }
    }
}