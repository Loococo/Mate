package app.loococo.data.model.response

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
    val id: Long = 0,
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
        name = "${user.firstName} ${user.lastName}"
    )
}