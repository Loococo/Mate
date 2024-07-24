package app.loococo.presentation.signup

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.loococo.domain.model.network.Resource
import app.loococo.domain.usecase.AuthUseCase
import app.loococo.presentation.login.LoginSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) :
    ContainerHost<SignUpState, SignUpSideEffect>, ViewModel() {
    override val container =
        container<SignUpState, SignUpSideEffect>(SignUpState())

    fun handleIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.EmailChanged -> updateEmail(intent.email)
            is SignUpIntent.PasswordChanged -> updatePassword(intent.password)
            is SignUpIntent.FirstNameChanged -> updateFirstName(intent.firstName)
            is SignUpIntent.LastNameChanged -> updateLastName(intent.lastName)
            is SignUpIntent.SignUpClicked -> signUp()
        }
    }

    private fun updateEmail(email: TextFieldValue) = intent {
        reduce { state.copy(email = email) }
    }

    private fun updatePassword(password: TextFieldValue) = intent {
        reduce { state.copy(password = password) }
    }

    private fun updateFirstName(firstName: TextFieldValue) = intent {
        reduce { state.copy(firstName = firstName) }
    }

    private fun updateLastName(lastName: TextFieldValue) = intent {
        reduce { state.copy(lastName = lastName) }
    }

    private fun signUp() = intent {
        reduce { state.copy(isLoading = true) }
        viewModelScope.launch {
            authUseCase.signUp(
                state.email.text,
                state.password.text,
                state.firstName.text,
                state.lastName.text
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        reduce { state.copy(isLoading = false) }
                        postSideEffect(SignUpSideEffect.NavigateToLogin)
                    }

                    is Resource.Error -> {
                        reduce { state.copy(isLoading = false) }
                        postSideEffect(SignUpSideEffect.ShowToast("error"))
                    }

                    is Resource.Message -> {
                        reduce { state.copy(isLoading = false) }
                        postSideEffect(SignUpSideEffect.ShowToast(it.message))
                    }
                }
            }
        }
    }
}