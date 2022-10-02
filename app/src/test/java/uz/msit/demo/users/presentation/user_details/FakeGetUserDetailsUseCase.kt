package uz.msit.demo.users.presentation.user_details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.msit.demo.core.utils.Either
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.model.UserDetails
import uz.msit.demo.users.domain.repository.UserRepository
import uz.msit.demo.users.domain.usecase.GetUserDetailsUseCase
import uz.msit.demo.users.domain.usecase.GetUsersUseCase

class FakeGetUserDetailsUseCase(repository: UserRepository) : GetUserDetailsUseCase(repository) {

    private val fakeFlow = MutableSharedFlow<Either<GetUsersFailure, UserDetails>>()

    suspend fun emit(value: Either<GetUsersFailure, UserDetails>) = fakeFlow.emit(value)

    override operator fun invoke(login: String): Flow<Either<GetUsersFailure, UserDetails>> {
        return fakeFlow
    }
}