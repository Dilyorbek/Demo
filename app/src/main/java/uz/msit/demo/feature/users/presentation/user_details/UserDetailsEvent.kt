package uz.msit.demo.feature.users.presentation.user_details

sealed class UserDetailsEvent {
    object GetUserDetails : UserDetailsEvent()
}
