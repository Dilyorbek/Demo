package uz.msit.demo.feature.users.data.repository

import uz.msit.demo.feature.users.domain.failure.GetUsersFailure
import uz.msit.demo.feature.users.domain.model.User
import uz.msit.demo.feature.users.domain.repository.UserRepository
import uz.msit.demo.feature.users.data.remote.data_source.UserRemoteDataSource
import uz.msit.demo.feature.users.domain.model.UserDetails
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val remoteDataSource: UserRemoteDataSource) : UserRepository {
    override suspend fun getUsers(): Result<List<User>> =
        try {
            Result.success(remoteDataSource.getUsers())
        } catch (ex: GetUsersFailure) {
            Result.failure(ex)
        }


    override suspend fun getUserDetails(login: String): Result<UserDetails> =
        try {
            Result.success(remoteDataSource.getUserDetails(login))
        } catch (ex: GetUsersFailure) {
            Result.failure(ex)
        }
}