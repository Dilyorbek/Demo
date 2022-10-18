package uz.msit.demo.feature.users.presentation.preview_param_provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uz.msit.demo.feature.users.domain.model.User

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