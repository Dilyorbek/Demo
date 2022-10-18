package uz.msit.demo.feature.users.presentation.user_details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.msit.demo.feature.users.domain.failure.GetUsersFailure
import uz.msit.demo.feature.users.domain.model.UserDetails
import uz.msit.demo.feature.users.domain.repository.UserRepository
import uz.msit.demo.feature.users.domain.usecase.GetUserDetailsUseCase

class FakeGetUserDetailsUseCase(repository: UserRepository) : GetUserDetailsUseCase(repository) {

    private var fakeResult: Result<UserDetails>? = null

    suspend fun emit(value:  Result<UserDetails>) {
        fakeResult = value
    }

    override suspend operator fun invoke(login: String): Result<UserDetails> {
        return fakeResult!!
    }
}