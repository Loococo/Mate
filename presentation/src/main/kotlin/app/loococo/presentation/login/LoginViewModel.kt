package app.loococo.presentation.login

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.loococo.domain.model.network.Resource
import app.loococo.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) :
    ContainerHost<LoginState, LoginSideEffect>, ViewModel() {

    override val container =
        container<LoginState, LoginSideEffect>(LoginState())

    fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> updateEmail(intent.email)
            is LoginIntent.PasswordChanged -> updatePassword(intent.password)
            is LoginIntent.LoginClicked -> login()
            is LoginIntent.SignUpClicked -> signUp()
        }
    }

    private fun updateEmail(email: TextFieldValue) = intent {
        reduce { state.copy(email = email) }
    }

    private fun updatePassword(password: TextFieldValue) = intent {
        reduce { state.copy(password = password) }
    }

    private fun login() = intent {
        reduce { state.copy(isLoading = true) }

        viewModelScope.launch {
            authUseCase.login(state.email.text, state.password.text).collect {
                reduce { state.copy(isLoading = false) }

                when (it) {
                    is Resource.Success -> {
                        postSideEffect(LoginSideEffect.NavigateToHome)
                    }

                    is Resource.Error -> {
                        postSideEffect(LoginSideEffect.ShowToast("error"))
                    }

                    is Resource.Message -> {
                        postSideEffect(LoginSideEffect.ShowToast(it.message))
                    }
                }
            }
        }
    }

    private fun signUp() = intent {
        postSideEffect(LoginSideEffect.NavigateToSignUp)
    }
}