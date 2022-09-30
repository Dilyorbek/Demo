package uz.msit.demo.users.presentation.user_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import uz.msit.demo.core.utils.fold
import uz.msit.demo.users.domain.usecase.GetUsersUseCase
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val _state = mutableStateOf(UsersState())
    val state: State<UsersState> = _state

    private var getUsersJob: Job? = null

    init {
        onEvent(UsersEvent.GetAll)
    }

    fun onEvent(event: UsersEvent) {
        when (event) {
            is UsersEvent.GetAll -> {
                getUsers()
            }
        }
    }

    private fun getUsers() {
        getUsersJob?.cancel()
        _state.value = _state.value.copy(isLoading = true, message = null)
        getUsersJob = getUsersUseCase()
            .onEach { it ->
                it.fold({
                    _state.value = _state.value.copy(message = "No network connection!", isLoading = false)
                }, { users ->
                    _state.value = state.value.copy(users = users, message = null, isLoading = false)
                })
            }
            .launchIn(viewModelScope)
    }
}