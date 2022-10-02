package uz.msit.demo.users.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.msit.demo.core.utils.Either
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.UserDetails
import uz.msit.demo.users.domain.repository.UserRepository
import javax.inject.Inject

open class GetUserDetailsUseCase @Inject constructor(private val repository: UserRepository) {
    open operator fun invoke(login: String): Flow<Either<GetUsersFailure, UserDetails>> {
        return repository.getUserDetails(login)
    }
}