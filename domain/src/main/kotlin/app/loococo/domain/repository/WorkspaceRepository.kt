package app.loococo.domain.repository

import app.loococo.domain.model.Workspace
import app.loococo.domain.model.network.Resource
import kotlinx.coroutines.flow.Flow

interface WorkspaceRepository {

    suspend fun findWorkspaceList(): Flow<Resource<List<Workspace>>>

    suspend fun create(name: String, slug: String): Flow<Resource<Unit>>
}