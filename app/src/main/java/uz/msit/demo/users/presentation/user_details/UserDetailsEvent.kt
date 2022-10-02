package uz.msit.demo.users.presentation.user_details

sealed class UserDetailsEvent {
    object GetUserDetails : UserDetailsEvent()
}
