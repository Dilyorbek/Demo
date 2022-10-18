package uz.msit.demo.feature.users.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.msit.demo.feature.users.domain.model.User
import uz.msit.demo.feature.users.domain.model.UserDetails

interface UserService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users/{login}")
    suspend fun getUserDetails(@Path("login") login: String): Response<UserDetails>
}