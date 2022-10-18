package uz.msit.demo.feature.users.presentation.user_list


import uz.msit.demo.core.utils.NoParameters
import uz.msit.demo.feature.users.domain.failure.GetUsersFailure
import uz.msit.demo.feature.users.domain.model.User
import uz.msit.demo.feature.users.domain.repository.UserRepository
import uz.msit.demo.feature.users.domain.usecase.GetUsersUseCase

class FakeGetUsersUseCase(repository: UserRepository) : GetUsersUseCase(repository) {

    private var fakeResult: Result<List<User>>? = null

    suspend fun emit(value: Result<List<User>>) {
        fakeResult = value
    }

    override suspend operator fun invoke(parameters: NoParameters): Result<List<User>> {
        return fakeResult!!
    }

}