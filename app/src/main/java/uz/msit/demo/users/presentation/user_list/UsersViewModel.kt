package uz.msit.demo.users.presentation.user_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.msit.demo.core.utils.fold
import uz.msit.demo.users.domain.usecase.GetUsersUseCase
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
            getUsersUseCase()
                .collect {
                    it.fold({
                        _state.value = _state.value.copy(message = "No network connection!", isLoading = false)
                    }, { users ->
                        _state.value = state.value.copy(data = users, message = null, isLoading = false)
                    })
                }
        }
    }
}