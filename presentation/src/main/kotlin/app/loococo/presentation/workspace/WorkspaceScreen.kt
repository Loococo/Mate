package app.loococo.presentation.workspace

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun WorkspaceRoute(showToast: (String) -> Unit) {
    WorkspaceScreen(showToast)
}

@Composable
fun WorkspaceScreen(showToast: (String) -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {

    }
}