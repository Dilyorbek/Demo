package uz.msit.demo.users.domain.failure

sealed class GetUsersFailure: Exception() {
    object NoNetworkConnection: GetUsersFailure()
    object UnknownError: GetUsersFailure()
    object CacheError: GetUsersFailure()
}