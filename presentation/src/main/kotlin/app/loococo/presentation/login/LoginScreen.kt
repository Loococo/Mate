package app.loococo.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.loococo.presentation.R
import app.loococo.presentation.component.MateBorderPasswordTextField
import app.loococo.presentation.component.MateBorderTextField
import app.loococo.presentation.component.MateLabelText
import app.loococo.presentation.component.MateLogoText
import app.loococo.presentation.component.MateRoundButton
import app.loococo.presentation.theme.Black

@Composable
internal fun LoginRoute() {
    LoginScreen()
}


@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = hiltViewModel()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    val sideEffectFlow = viewModel.container.sideEffectFlow

    LaunchedEffect(sideEffectFlow) {
        sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is LoginSideEffect.NavigateToHome -> {
                }
                is LoginSideEffect.ShowToast -> {
                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
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
            }
        )
    }
}

@Composable
fun LoginHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        MateLogoText(text = "Mate", fontWeight = FontWeight.Bold)
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.mate_icon),
            contentDescription = "logo"
        )
    }
}

@Composable
fun LoginContent(
    email: TextFieldValue,
    password: TextFieldValue,
    changeEmail: (TextFieldValue) -> Unit,
    changePassword: (TextFieldValue) -> Unit,
    login: () -> Unit
) {
    Column(modifier = Modifier.padding(20.dp)) {

        MateBorderTextField(
            text = email,
            hint = "email",
            onValueChange = changeEmail
        )

        Spacer(modifier = Modifier.height(8.dp))

        MateBorderPasswordTextField(
            text = password,
            hint = "password",
            onValueChange = changePassword
        )

        Spacer(modifier = Modifier.height(16.dp))

        MateRoundButton(
            text = "Login",
            onClick = login
        )

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            MateLabelText(
                text = "회원가입",
                modifier = Modifier.clickable {

                },
                color = Black
            )
        }
    }
}