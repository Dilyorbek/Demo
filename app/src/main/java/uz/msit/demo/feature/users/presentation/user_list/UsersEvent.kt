package uz.msit.demo.feature.users.presentation.user_list

sealed class UsersEvent {
    object GetAll : UsersEvent()
}
