package uz.msit.demo.users.domain.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uz.msit.demo.users.domain.model.User
import uz.msit.demo.users.domain.model.UserDetails

class UserProvider : PreviewParameterProvider<User> {
    override val values = sequenceOf(
        User(
            1,
            "mojombo",
            "https://avatars.githubusercontent.com/u/1?v=4"
        ),
        User(
            21,
            "technoweenie",
            "https://avatars.githubusercontent.com/u/21?v=4"
        )
    )
}