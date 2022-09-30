package uz.msit.demo.users.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.msit.demo.core.utils.Either
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.repository.UserRepository
import javax.inject.Inject

open class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {
    open operator fun invoke(): Flow<Either<GetUsersFailure, List<User>>> {
        return repository.getUsers()
    }
}