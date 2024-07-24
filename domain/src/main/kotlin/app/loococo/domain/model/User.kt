package app.loococo.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String
)

data class Token(
    var accessToken: String,
    var refreshToken: String
)