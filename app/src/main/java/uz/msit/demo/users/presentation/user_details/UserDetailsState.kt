package uz.msit.demo.users.presentation.user_details

import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.model.UserDetails

data class UserDetailsState(
    var data: UserDetails? = null,
    var message: String? = null,
    var isLoading: Boolean = false
)