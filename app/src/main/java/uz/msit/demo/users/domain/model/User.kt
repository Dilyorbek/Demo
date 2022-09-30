package uz.msit.demo.users.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    @SerializedName("login")
    val username: String,
    @SerializedName("avatar_url")
    val photo: String
)
