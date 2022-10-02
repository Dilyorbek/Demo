package uz.msit.demo.utils

import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.model.UserDetails

object TestData {
    const val testUsersJson = """[
                {"id": 1, "login": "mojombo", "avatar_url": "https://avatars.githubusercontent.com/u/1?v=4"},
                {"id": 3, "login": "pjhyett", "avatar_url": "https://avatars.githubusercontent.com/u/3?v=4"}
            ]"""

    val testUsers = listOf(
        User(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
        User(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4")
    )

    const val testUserDetailsJson = """{
                "id": 21,
                "login": "technoweenie",
                "avatar_url": "https://avatars.githubusercontent.com/u/21?v=4",
                "public_repos": 178,
                "public_gists": 106,
                "followers": 2589,
                "following": 18
           }"""

    val testUserDetails = UserDetails(
        21,
        "technoweenie",
        "https://avatars.githubusercontent.com/u/21?v=4",
        178,
        106,
        2589,
        18
    )
}