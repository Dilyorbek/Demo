package uz.msit.demo.feature.users.domain.failure

sealed class GetUsersFailure: Exception() {
    object NoNetworkConnection: GetUsersFailure()
    object UnknownError: GetUsersFailure()
}