package uz.msit.demo.users.domain.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uz.msit.demo.users.domain.model.UserDetails

class UserDetailsProvider : PreviewParameterProvider<UserDetails> {
    override val values = sequenceOf(
        UserDetails(
            1,
            "mojombo",
            "https://avatars.githubusercontent.com/u/1?v=4",
            64,
            62,
            23194,
            11
        ),
        UserDetails(
            21,
            "technoweenie",
            "https://avatars.githubusercontent.com/u/21?v=4",
            178,
            106,
            258,
            18
        )
    )
}
