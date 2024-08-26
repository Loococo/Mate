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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import app.loococo.presentation.theme.Black
import app.loococo.presentation.theme.Gray4
import app.loococo.presentation.theme.White

@Composable
fun MateBorderTextField(
    text: TextFieldValue,
    hint: String,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (TextFieldValue) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(White, RoundedCornerShape(10.dp))
            .border(1.dp, Black, RoundedCornerShape(10.dp))
            .padding(10.dp, 5.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                color = Black,
            ),
            cursorBrush = SolidColor(Black),
            decorationBox = { innerTextField ->
                if (text.text.isEmpty()) {
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
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation
        )
    }
}

@Composable
fun MateBorderPasswordTextField(
    text: TextFieldValue,
    hint: String,
    imeAction: ImeAction = ImeAction.Done,
    onValueChange: (TextFieldValue) -> Unit
) {
    MateBorderTextField(
        text = text,
        hint = hint,
        imeAction = imeAction,
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = onValueChange
    )
}

@Composable
fun MateBorderSlugTextField(
    text: TextFieldValue,
    hint: String,
    onValueChange: (TextFieldValue) -> Unit
) {
    MateBorderTextField(
        text = text,
        hint = hint,
        keyboardType = KeyboardType.Text,
        onValueChange = { newTextFieldValue ->
            val filteredText = newTextFieldValue.text
                .filter { it.isLowerCase() || it.isDigit() || it.isWhitespace() }
                .replace(' ', '-')  // 공백을 '-'로 치환
            onValueChange(newTextFieldValue.copy(text = filteredText))
        }
    )
}