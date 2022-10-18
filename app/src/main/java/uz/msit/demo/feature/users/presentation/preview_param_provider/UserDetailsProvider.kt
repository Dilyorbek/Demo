package uz.msit.demo.feature.users.presentation.preview_param_provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uz.msit.demo.feature.users.domain.model.UserDetails

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
        )
    )
}
