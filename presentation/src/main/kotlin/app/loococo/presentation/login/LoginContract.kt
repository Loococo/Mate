package app.loococo.presentation.login

import androidx.compose.ui.text.input.TextFieldValue

data class LoginState(
    val email: TextFieldValue = TextFieldValue(""),
    val password: TextFieldValue = TextFieldValue(""),
    val isLoading: Boolean = false
)

sealed class LoginSideEffect {
    data object NavigateToHome : LoginSideEffect()
    data object NavigateToSignUp : LoginSideEffect()
    data class ShowToast(val message: String) : LoginSideEffect()
}

sealed class LoginIntent {
    data class EmailChanged(val email: TextFieldValue) : LoginIntent()
    data class PasswordChanged(val password: TextFieldValue) : LoginIntent()
    data object LoginClicked : LoginIntent()
    data object SignUpClicked : LoginIntent()
}