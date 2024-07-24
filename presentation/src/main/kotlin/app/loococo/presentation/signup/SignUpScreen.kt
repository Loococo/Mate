package app.loococo.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.loococo.presentation.component.CircularProgress
import app.loococo.presentation.component.MateBorderPasswordTextField
import app.loococo.presentation.component.MateBorderTextField
import app.loococo.presentation.component.MateLogoText
import app.loococo.presentation.component.MateRoundButton
import app.loococo.presentation.component.MateTitleText

@Composable
internal fun SignUpRoute(
    goToLogin: () -> Unit,
    showToast: (String) -> Unit
) {
    SignUpScreen(goToLogin, showToast)
}

@Composable
fun SignUpScreen(
    goToLogin: () -> Unit,
    showToast: (String) -> Unit
) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    val sideEffectFlow = viewModel.container.sideEffectFlow
    val scrollState = rememberScrollState()

    LaunchedEffect(sideEffectFlow) {
        sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is SignUpSideEffect.NavigateToLogin -> goToLogin()
                is SignUpSideEffect.ShowToast -> showToast(sideEffect.message)
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .height(IntrinsicSize.Max)
    ) {
        SignUpHeader()
        SignUpContent(
            email = state.email,
            password = state.password,
            firstName = state.firstName,
            lastName = state.lastName,
            changeEmail = {
                viewModel.handleIntent(SignUpIntent.EmailChanged(it))
            },
            changePassword = {
                viewModel.handleIntent(SignUpIntent.PasswordChanged(it))
            },
            changeFirstName = {
                viewModel.handleIntent(SignUpIntent.FirstNameChanged(it))
            },
            changeLastName = {
                viewModel.handleIntent(SignUpIntent.LastNameChanged(it))
            },
            signUp = {
                viewModel.handleIntent(SignUpIntent.SignUpClicked)
            }
        )
    }


    if (state.isLoading) {
        CircularProgress()
    }
}

@Composable
fun SignUpHeader() {
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
fun SignUpContent(
    email: TextFieldValue,
    password: TextFieldValue,
    firstName: TextFieldValue,
    lastName: TextFieldValue,
    changeEmail: (TextFieldValue) -> Unit,
    changePassword: (TextFieldValue) -> Unit,
    changeFirstName: (TextFieldValue) -> Unit,
    changeLastName: (TextFieldValue) -> Unit,
    signUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            MateTitleText(text = "Sign up", fontWeight = FontWeight.Bold)

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
                imeAction = ImeAction.Next,
                onValueChange = changePassword
            )

            Spacer(modifier = Modifier.height(10.dp))

            MateBorderTextField(
                text = firstName,
                hint = "firstName",
                onValueChange = changeFirstName
            )

            Spacer(modifier = Modifier.height(10.dp))

            MateBorderTextField(
                text = lastName,
                hint = "lastName",
                imeAction = ImeAction.Done,
                onValueChange = changeLastName
            )

            Spacer(modifier = Modifier.height(5.dp))

            MateRoundButton(
                text = "Continue",
                onClick = signUp
            )

            Spacer(modifier = Modifier.height(5.dp))
        }
        Spacer(modifier = Modifier.weight(2f))
    }
}