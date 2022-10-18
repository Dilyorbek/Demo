package uz.msit.demo.feature.users.domain.repository

import uz.msit.demo.feature.users.domain.model.User
import uz.msit.demo.feature.users.domain.model.UserDetails

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUserDetails(login: String): Result<UserDetails>
}