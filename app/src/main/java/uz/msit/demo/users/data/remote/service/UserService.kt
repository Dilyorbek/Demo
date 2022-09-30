package uz.msit.demo.users.data.remote.service

import retrofit2.Response
import retrofit2.http.GET
import uz.msit.demo.users.domain.model.User

interface UserService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}