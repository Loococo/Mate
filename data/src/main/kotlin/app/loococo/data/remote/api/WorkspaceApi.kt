package app.loococo.data.remote.api

import app.loococo.data.model.response.WorkspaceResponse
import retrofit2.Response
import retrofit2.http.GET

interface WorkspaceApi {

    @GET("/api/workspaces")
    suspend fun findWorkspaceList(): Response<List<WorkspaceResponse>>

}