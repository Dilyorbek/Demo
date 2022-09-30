package uz.msit.demo.users.presentation.user_list

sealed class UsersEvent {
    object GetAll : UsersEvent()
}
