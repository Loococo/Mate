package app.loococo.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface WorkspaceApi {

    @GET("/api/workspaces")
    suspend fun findWorkspaceList(): Response<Unit>

}