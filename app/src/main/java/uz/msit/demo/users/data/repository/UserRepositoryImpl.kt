package uz.msit.demo.users.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.msit.demo.core.utils.Either
import uz.msit.demo.core.utils.Left
import uz.msit.demo.core.utils.Right
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.repository.UserRepository
import uz.msit.demo.users.data.remote.data_source.UserRemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val remoteDataSource: UserRemoteDataSource): UserRepository {
    override fun getUsers(): Flow<Either<GetUsersFailure, List<User>>>  = flow {
        try {
           emit(Right(remoteDataSource.getUsers()))
        } catch (ex: GetUsersFailure) {
            emit(Left(ex))
        }
        Result.success(emptyList<User>())
        Result.failure<GetUsersFailure>(GetUsersFailure.UnknownError)
    }
}