package app.loococo.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.loococo.presentation.R
import app.loococo.presentation.component.MateBorderPasswordTextField
import app.loococo.presentation.component.MateBorderTextField
import app.loococo.presentation.component.MateLogoText
import app.loococo.presentation.component.MateRoundButton

@Composable
internal fun LoginRoute() {
    LoginScreen()
}


@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            MateLogoText(text = "Mate", fontWeight = FontWeight.Bold)
            Image(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.mate_icon),
                contentDescription = "logo"
            )
        }

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            MateBorderTextField(
                text = email,
                hint = "email",
                onValueChange = {
                    email = it
                }
            )

            Spacer(modifier = Modifier.height(8.dp))
            MateBorderPasswordTextField(
                text = password,
                hint = "password",
                onValueChange = {
                    password = it
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            MateRoundButton(
                text = "Login",
                onClick = {

                }
            )
        }
    }
}