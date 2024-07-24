package app.loococo.data.model.response

import app.loococo.domain.model.Token
import app.loococo.domain.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("user")
    val user: LoginUserData,
    @SerializedName("tokens")
    var tokens: LoginTokenData
)

data class LoginUserData(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("firstName")
    val firstName: String = "",
    @SerializedName("lastName")
    val lastName: String = "",
)

data class LoginTokenData(
    @SerializedName("accessToken")
    var accessToken: String = "",
    @SerializedName("refreshToken")
    var refreshToken: String = ""
)

fun LoginResponse.toUser(): User {
    return User(
        id = user.id,
        name = "${user.firstName} ${user.lastName}",
        email = user.email
    )
}

fun LoginTokenData.toToken(): Token {
    return Token(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}