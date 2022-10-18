package uz.msit.demo.feature.users.presentation.user_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import uz.msit.demo.feature.users.domain.usecase.GetUsersUseCase

class FakeUsersViewModel(getUsersUseCase: GetUsersUseCase) : UsersViewModel(getUsersUseCase) {

    private val _state = mutableStateOf(UsersState())
    override val state: State<UsersState> = _state

    var lastEvent: UsersEvent? = null

    fun setState(state: UsersState) {
        _state.value = state
    }

    override fun onEvent(event: UsersEvent) {
        lastEvent = event
    }
}