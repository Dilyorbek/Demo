package uz.msit.demo.feature.users.presentation.user_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.msit.demo.feature.users.domain.usecase.GetUserDetailsUseCase
import javax.inject.Inject

@HiltViewModel
open class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(UserDetailsState())
    open val state: State<UserDetailsState> = _state
    private var login: String = savedStateHandle.get<String>("login")?:""

    init {
        onEvent(UserDetailsEvent.GetUserDetails)
    }

    open fun onEvent(event: UserDetailsEvent) = when (event) {
        is UserDetailsEvent.GetUserDetails -> {
            getUserDetails()
        }
    }

    private fun getUserDetails() {
        _state.value = _state.value.copy(isLoading = true, message = null)
        viewModelScope.launch {
            val result = getUserDetailsUseCase(login)
            if (result.isSuccess) {
                _state.value = state.value.copy(result.getOrNull(), null, false)
            } else {
                _state.value = _state.value.copy(message = "No network connection!", isLoading = false)
            }
        }
    }
}