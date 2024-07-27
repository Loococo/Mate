package app.loococo.data.model.response

import app.loococo.domain.model.Workspace

data class WorkspaceResponse(
    val id: String,
    val name: String,
)

fun WorkspaceResponse.toWorkspace(): Workspace {
    return Workspace(
        id = id,
        name = name
    )
}