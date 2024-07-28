package app.loococo.mate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.loococo.domain.model.state.SplashState
import app.loococo.mate.ui.MateApp
import app.loococo.mate.ui.rememberMateAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        setContent {
            val splashState by viewModel.splashState.collectAsStateWithLifecycle()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val authenticatedState by viewModel.authenticatedState.collectAsStateWithLifecycle()

            splashScreen.setKeepOnScreenCondition {
                splashState == SplashState.Loading
            }

            MateApp(
                appState = rememberMateAppState(
                    uiState = uiState,
                    authenticatedState = authenticatedState
                ),
                showToast = {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}