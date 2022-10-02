package uz.msit.demo.users.presentation.user_details

sealed class UserDetailsEvent {
    data class GetUserDetails(val login: String) : UserDetailsEvent()
}
