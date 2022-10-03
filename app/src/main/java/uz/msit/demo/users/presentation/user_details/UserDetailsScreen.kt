package uz.msit.demo.users.presentation.user_details

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import uz.msit.demo.users.presentation.user_details.views.UserDetailsListView
import uz.msit.demo.users.presentation.views.ErrorView
import uz.msit.demo.users.presentation.views.LoadingView

@Composable
fun UserDetailsScreen(
    navigateToBack: () -> Unit,
    viewModel: UserDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "User Details") },
                navigationIcon = {
                    IconButton(onClick = navigateToBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                })
        },
        scaffoldState = scaffoldState
    ) {
        if (state.data != null) {
            UserDetailsListView(state.data!!)
        } else if (state.isLoading) {
            LoadingView()
        } else if (!state.message.isNullOrEmpty()) {
            ErrorView(
                state.message!!,
                "Retry",
                onButtonClick = {
                    viewModel.onEvent(UserDetailsEvent.GetUserDetails)
                }
            )
        }
    }
}