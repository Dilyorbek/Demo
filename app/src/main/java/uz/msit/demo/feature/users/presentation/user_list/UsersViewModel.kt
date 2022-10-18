package uz.msit.demo.feature.users.presentation.user_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uz.msit.demo.core.utils.NoParameters
import uz.msit.demo.feature.users.domain.usecase.GetUsersUseCase
import javax.inject.Inject

@HiltViewModel
open class UsersViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val _state = mutableStateOf(UsersState())
    open val state: State<UsersState> = _state

    private var getUsersJob: Job? = null

    init {
        onEvent(UsersEvent.GetAll)
    }

    open fun onEvent(event: UsersEvent) = when (event) {
        is UsersEvent.GetAll -> {
            getUsers()
        }
    }

    private fun getUsers() {
        getUsersJob?.cancel()
        _state.value = _state.value.copy(message = null, isLoading = true)
        getUsersJob = viewModelScope.launch {
            val result = getUsersUseCase(NoParameters)
            if (result.isSuccess) {
                _state.value = state.value.copy(data = result.getOrThrow(), message = null, isLoading = false)
            } else {
                _state.value = _state.value.copy(data = emptyList(), message = "No network connection!", isLoading = false)
            }
        }
    }
}