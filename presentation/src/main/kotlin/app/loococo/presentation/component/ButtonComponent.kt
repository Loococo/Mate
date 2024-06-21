package app.loococo.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.loococo.presentation.theme.Blue1
import app.loococo.presentation.theme.Blue2
import app.loococo.presentation.theme.White

@Composable
fun MateRoundButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue1,
            contentColor = White,
            disabledContainerColor = Blue2,
            disabledContentColor = White,
        ),
    ) {
        MateLabelText(
            text = text,
            modifier = Modifier.padding(5.dp),
            color = White
        )
    }
}