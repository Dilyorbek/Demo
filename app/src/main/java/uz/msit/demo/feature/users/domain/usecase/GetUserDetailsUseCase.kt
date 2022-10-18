package uz.msit.demo.feature.users.domain.usecase

import uz.msit.demo.core.domain.usecase.UseCase
import uz.msit.demo.feature.users.domain.model.UserDetails
import uz.msit.demo.feature.users.domain.repository.UserRepository
import javax.inject.Inject

open class GetUserDetailsUseCase @Inject constructor(private val repository: UserRepository) : UseCase<String, UserDetails>() {
    override suspend operator fun invoke(login: String): Result<UserDetails> {
        return repository.getUserDetails(login)
    }
}