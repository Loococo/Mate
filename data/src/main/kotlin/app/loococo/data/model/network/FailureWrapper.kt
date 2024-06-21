package app.loococo.data.model.network

import com.google.gson.annotations.SerializedName

data class FailureWrapper(
    @SerializedName("msgCode")
    var messageCode: String? = null,

    @SerializedName("msg")
    var message: String? = null
)