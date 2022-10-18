package uz.msit.demo.feature.users.presentation.user_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import uz.msit.demo.feature.users.domain.usecase.GetUserDetailsUseCase

class FakeUserDetailsViewModel(getUserDetailsUseCase: GetUserDetailsUseCase, savedStateHandle: SavedStateHandle) : UserDetailsViewModel
    (getUserDetailsUseCase, savedStateHandle) {

    private val _state = mutableStateOf(UserDetailsState())
    override val state: State<UserDetailsState> = _state

    var lastEvent: UserDetailsEvent? = null

    fun setState(state: UserDetailsState) {
        _state.value = state
    }

    override fun onEvent(event: UserDetailsEvent) {
        lastEvent = event
    }
}