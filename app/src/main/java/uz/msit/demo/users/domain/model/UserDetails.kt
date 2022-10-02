package uz.msit.demo.users.domain.model

data class UserDetails(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val public_repos: Int,
    val public_gists: Int,
    val followers: Int,
    val following: Int
)


