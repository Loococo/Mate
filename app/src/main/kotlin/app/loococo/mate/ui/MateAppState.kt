package app.loococo.mate.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.loococo.domain.model.state.MainUiState

@Composable
fun rememberMateAppState(
    navController: NavHostController = rememberNavController(),
): MateAppState {
    return remember(navController) {
        MateAppState(
            navController = navController
        )
    }
}

@Stable
class MateAppState(
    val navController: NavHostController
) {
}