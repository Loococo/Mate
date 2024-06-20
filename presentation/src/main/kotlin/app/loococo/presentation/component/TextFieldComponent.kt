package app.loococo.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import app.loococo.presentation.theme.Black
import app.loococo.presentation.theme.Gray4
import app.loococo.presentation.theme.White

@Composable
fun MateBorderTextField(
    text: String,
    hint: String,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(White, RoundedCornerShape(10.dp))
            .border(1.dp, Black, RoundedCornerShape(10.dp))
            .padding(15.dp, 5.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(
                color = Black,
            ),
            cursorBrush = SolidColor(Black),
            decorationBox = { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        style = TextStyle(
                            color = Gray4,
                        )
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction,
                keyboardType = keyboardType
            ),
            keyboardActions = keyboardActions
        )
    }
}

@Composable
fun MateBorderPasswordTextField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    MateBorderTextField(
        text = text,
        hint = hint,
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Password,
        onValueChange = onValueChange
    )
}