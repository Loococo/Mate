package app.loococo.data.ext

import android.util.Patterns

fun String.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() = !isNullOrEmpty() && 7 < length && length < 33

fun String.isValidName() = !isNullOrEmpty() && 1 < length && length < 33