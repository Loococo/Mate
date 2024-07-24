package app.loococo.presentation.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val signUpRoute = "sign_up_route"

fun NavGraphBuilder.signUpScreen(
    goToLogin: () -> Unit,
    showToast: (String) -> Unit
) {
    composable(route = signUpRoute) {
        SignUpRoute(goToLogin, showToast)
    }
}

fun NavController.navigateToSignUp(){
    this.navigate(route = signUpRoute)
}