package app.loococo.mate.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.loococo.mate.navigation.MateNavHost
import app.loococo.presentation.theme.MateTheme
import app.loococo.presentation.theme.White

@Composable
fun MateApp(
    appState: MateAppState = rememberMateAppState(),
    showToast: (String) -> Unit
) {
    MateTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = White
        ) {
            MateNavHost(appState, showToast)
        }
    }
}