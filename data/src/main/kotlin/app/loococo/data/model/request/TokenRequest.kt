package app.loococo.data.model.request

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("accessToken")
    var accessToken: String,
    @SerializedName("refreshToken")
    var refreshToken: String
)