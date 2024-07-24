package app.loococo.data.model.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("firstName")
    var firstName: String = "",
    @SerializedName("lastName")
    var lastName: String = ""
)