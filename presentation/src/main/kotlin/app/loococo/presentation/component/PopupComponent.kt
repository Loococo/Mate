package app.loococo.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.loococo.presentation.theme.White

@Composable
fun MateWorkspaceCreatePopup(
    onDismissRequest: () -> Unit,
    name: TextFieldValue,
    slug: TextFieldValue,
    changeName: (TextFieldValue) -> Unit,
    changeSlug: (TextFieldValue) -> Unit,
    create: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest.invoke() }
    ) {
        Column(
            modifier = Modifier
                .background(White, RoundedCornerShape(10.dp))
                .padding(15.dp)
        ) {
            MateBodyText(text = "name", fontWeight = FontWeight.Bold)

            HeightSpacer(height = 5)

            MateBorderTextField(
                text = name,
                hint = "name",
                onValueChange = changeName
            )

            HeightSpacer(height = 10)

            MateBodyText(text = "slug", fontWeight = FontWeight.Bold)

            HeightSpacer(height = 5)

            MateBorderSlugTextField(
                text = slug,
                hint = "slug",
                onValueChange = changeSlug
            )

            HeightSpacer(height = 20)

            Row {
                MateRoundButton(
                    modifier = Modifier.weight(1f),
                    text = "Cancel",
                    onClick = {
                        onDismissRequest.invoke()
                    }
                )

                WidthSpacer(width = 10)

                MateRoundButton(
                    modifier = Modifier.weight(1f),
                    text = "Ok",
                    onClick = create
                )
            }
        }
    }
}