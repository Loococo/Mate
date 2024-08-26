package app.loococo.data.remote.manger.impl

import app.loococo.data.model.network.suspendResponseResult
import app.loococo.data.model.request.WorkspaceRequest
import app.loococo.data.model.response.WorkspaceResponse
import app.loococo.data.remote.api.WorkspaceApi
import app.loococo.data.remote.manger.WorkspaceDataSource
import app.loococo.domain.model.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkspaceDataSourceImpl @Inject constructor(
    private val workspaceApi: WorkspaceApi
) : WorkspaceDataSource {
    override suspend fun findWorkspaceList(): Flow<Resource<List<WorkspaceResponse>>> =
        suspendResponseResult { workspaceApi.findWorkspaceList() }

    override suspend fun create(request: WorkspaceRequest): Flow<Resource<Unit>> =
        suspendResponseResult { workspaceApi.create(request) }
}
