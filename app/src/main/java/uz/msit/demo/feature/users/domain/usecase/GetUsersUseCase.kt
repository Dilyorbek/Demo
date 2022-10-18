package uz.msit.demo.feature.users.domain.usecase

import uz.msit.demo.core.domain.usecase.UseCase
import uz.msit.demo.core.utils.NoParameters
import uz.msit.demo.feature.users.domain.model.User
import uz.msit.demo.feature.users.domain.repository.UserRepository
import javax.inject.Inject

open class GetUsersUseCase @Inject constructor(private val repository: UserRepository): UseCase<NoParameters, List<User>>() {
    open override suspend fun invoke(parameters: NoParameters): Result<List<User>> {
        return repository.getUsers()
    }
}