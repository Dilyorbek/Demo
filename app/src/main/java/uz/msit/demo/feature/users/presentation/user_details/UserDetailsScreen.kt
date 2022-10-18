package uz.msit.demo.feature.users.presentation.user_details

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import uz.msit.demo.feature.users.presentation.preview_param_provider.Tags
import uz.msit.demo.feature.users.presentation.user_details.views.UserDetailsListView
import uz.msit.demo.feature.users.presentation.views.ErrorView
import uz.msit.demo.feature.users.presentation.views.LoadingView

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
                modifier = Modifier.testTag(Tags.TOP_BAR),
                title = { Text(text = "User Details") },
                navigationIcon = {
                    IconButton(onClick = navigateToBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = Tags.BACK_BUTTON
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