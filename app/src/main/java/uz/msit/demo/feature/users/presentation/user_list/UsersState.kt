package uz.msit.demo.feature.users.presentation.user_list

import uz.msit.demo.feature.users.domain.model.User

data class UsersState(
    var data: List<User> = emptyList(),
    var message: String? = null,
    var isLoading: Boolean = false
)