package uz.msit.demo.users.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val login: String,
    val avatar_url: String
)
