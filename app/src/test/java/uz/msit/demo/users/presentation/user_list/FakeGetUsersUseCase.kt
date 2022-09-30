package uz.msit.demo.users.presentation.user_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.msit.demo.core.utils.Either
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.repository.UserRepository
import uz.msit.demo.users.domain.usecase.GetUsersUseCase

class FakeGetUsersUseCase(repository: UserRepository) : GetUsersUseCase(repository) {

    private val fakeFlow = MutableSharedFlow<Either<GetUsersFailure, List<User>>>()

    suspend fun emit(value: Either<GetUsersFailure, List<User>>) = fakeFlow.emit(value)

    override operator fun invoke(): Flow<Either<GetUsersFailure, List<User>>> {
        return fakeFlow
    }

}