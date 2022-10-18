package uz.msit.demo.feature.users.presentation.user_details

import uz.msit.demo.feature.users.domain.model.UserDetails

data class UserDetailsState(
    var data: UserDetails? = null,
    var message: String? = null,
    var isLoading: Boolean = false
)