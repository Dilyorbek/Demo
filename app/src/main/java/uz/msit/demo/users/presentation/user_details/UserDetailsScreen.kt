package uz.msit.demo.users.presentation.user_details

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import uz.msit.demo.users.presentation.components.ErrorView
import uz.msit.demo.users.presentation.components.LoadingView

@Composable
fun UserDetailsScreen(
    navigateToDetails: (userId: String) -> Unit,
    viewModel: UserDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        if (state.data != null) {
//            UserDetails
//            (state.data, onItemClick = {
//                navigateToDetails(it.login)
//            })
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