package uz.msit.demo.users.data.remote.data_source

import uz.msit.demo.users.data.remote.service.UserService
import uz.msit.demo.users.domain.failure.GetUsersFailure
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.model.UserDetails
import java.io.IOException
import javax.inject.Inject

interface UserRemoteDataSource {

    @Throws(GetUsersFailure::class)
    suspend fun getUsers(): List<User>

    @Throws(GetUsersFailure::class)
    suspend fun getUserDetails(login: String): UserDetails
}

class UserRemoteDataSourceImpl @Inject constructor(private val userService: UserService) : UserRemoteDataSource {

    @Throws(GetUsersFailure::class)
    override suspend fun getUsers(): List<User> {
        try {
            val response = userService.getUsers()
            if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            } else {
                throw GetUsersFailure.UnknownError
            }
        } catch (ex: IOException) {
            throw GetUsersFailure.NoNetworkConnection
        }
    }

    @Throws(GetUsersFailure::class)
    override suspend fun getUserDetails(login: String): UserDetails {
        try {
            val response = userService.getUserDetails(login)
            if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            } else {
                throw GetUsersFailure.UnknownError
            }
        } catch (ex: IOException) {
            throw GetUsersFailure.NoNetworkConnection
        }
    }
}