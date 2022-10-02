package uz.msit.demo.users.presentation.user_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.msit.demo.core.utils.fold
import uz.msit.demo.users.domain.usecase.GetUserDetailsUseCase
import uz.msit.demo.users.domain.usecase.GetUsersUseCase
import uz.msit.demo.users.presentation.user_list.UsersEvent
import uz.msit.demo.users.presentation.user_list.UsersState
import javax.inject.Inject

@HiltViewModel
open class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(UserDetailsState())
    open val state: State<UserDetailsState> = _state

    init {
        savedStateHandle.get<String>("login")?.let { login ->
            onEvent(UserDetailsEvent.GetUserDetails(login))
        }
    }

    open fun onEvent(event: UserDetailsEvent) = when (event) {
        is UserDetailsEvent.GetUserDetails -> {
            getUserDetails(event.login)
        }
    }

    private fun getUserDetails(login: String) {
        _state.value = _state.value.copy(isLoading = true, message = null)
        viewModelScope.launch {
            getUserDetailsUseCase(login).first().fold(
                {
                    _state.value = _state.value.copy(message = "No network connection!", isLoading = false)
                }, {
                    _state.value = state.value.copy(it, null, false)
                })
        }
    }
}