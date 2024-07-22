package app.loococo.presentation.signup

import androidx.compose.ui.text.input.TextFieldValue

data class SignUpState(
    val email: TextFieldValue = TextFieldValue(""),
    val password: TextFieldValue = TextFieldValue(""),
    val firstName: TextFieldValue = TextFieldValue(""),
    val lastName: TextFieldValue = TextFieldValue(""),
    val isLoading: Boolean = false
)

sealed class SignUpSideEffect {
    data object NavigateToHome : SignUpSideEffect()
    data class ShowToast(val message: String) : SignUpSideEffect()
}

sealed class SignUpIntent {
    data class EmailChanged(val email: TextFieldValue) : SignUpIntent()
    data class PasswordChanged(val password: TextFieldValue) : SignUpIntent()
    data class FirstNameChanged(val firstName: TextFieldValue) : SignUpIntent()
    data class LastNameChanged(val lastName: TextFieldValue) : SignUpIntent()
    data object SignUpClicked : SignUpIntent()
}