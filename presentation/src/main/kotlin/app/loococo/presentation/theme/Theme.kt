package app.loococo.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MateTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme {
        content.invoke()
    }
}