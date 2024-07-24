package app.loococo.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.loococo.presentation.component.CircularProgress
import app.loococo.presentation.component.MateBorderPasswordTextField
import app.loococo.presentation.component.MateBorderTextField
import app.loococo.presentation.component.MateLabelText
import app.loococo.presentation.component.MateLogoText
import app.loococo.presentation.component.MateRoundButton
import app.loococo.presentation.component.MateTitleText

@Composable
internal fun LoginRoute(
    goToHome: () -> Unit,
    goToSignUp: () -> Unit,
    showToast: (String) -> Unit
) {
    LoginScreen(goToHome, goToSignUp, showToast)
}


@Composable
fun LoginScreen(
    goToHome: () -> Unit,
    goToSignUp: () -> Unit,
    showToast: (String) -> Unit
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    val sideEffectFlow = viewModel.container.sideEffectFlow

    LaunchedEffect(sideEffectFlow) {
        sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is LoginSideEffect.NavigateToHome -> goToHome()
                LoginSideEffect.NavigateToSignUp -> goToSignUp()
                is LoginSideEffect.ShowToast -> showToast(sideEffect.message)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LoginHeader()
        LoginContent(
            email = state.email,
            password = state.password,
            changeEmail = {
                viewModel.handleIntent(LoginIntent.EmailChanged(it))
            },
            changePassword = {
                viewModel.handleIntent(LoginIntent.PasswordChanged(it))
            },
            login = {
                viewModel.handleIntent(LoginIntent.LoginClicked)
            },
            signUp = {
                viewModel.handleIntent(LoginIntent.SignUpClicked)
            }
        )
    }

    if (state.isLoading) {
        CircularProgress()
    }
}

@Composable
fun LoginHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MateLogoText(text = "Mate", fontWeight = FontWeight.Light)
    }
}

@Composable
fun LoginContent(
    email: TextFieldValue,
    password: TextFieldValue,
    changeEmail: (TextFieldValue) -> Unit,
    changePassword: (TextFieldValue) -> Unit,
    login: () -> Unit,
    signUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            MateTitleText(text = "Log in", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(20.dp))

            MateBorderTextField(
                text = email,
                hint = "mate@mate.com",
                onValueChange = changeEmail
            )
            Spacer(modifier = Modifier.height(10.dp))

            MateBorderPasswordTextField(
                text = password,
                hint = "password",
                onValueChange = changePassword
            )

            Spacer(modifier = Modifier.height(5.dp))

            MateRoundButton(
                text = "Continue",
                onClick = login
            )

            Spacer(modifier = Modifier.height(5.dp))

            Box(modifier = Modifier.clickable { signUp() }) {
                MateLabelText(text = "sign up")
            }
        }
        Spacer(modifier = Modifier.weight(2f))
    }
}