package app.loococo.data.model.request

import com.google.gson.annotations.SerializedName

data class WorkspaceRequest(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("slug")
    var slug: String = "",
)