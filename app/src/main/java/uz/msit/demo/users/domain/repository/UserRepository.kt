package uz.msit.demo.users.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.msit.demo.core.utils.Either
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.failure.GetUsersFailure

interface UserRepository {
    fun getUsers(): Flow<Either<GetUsersFailure, List<User>>>
}