package app.loococo.data.repository

import app.loococo.data.model.response.toWorkspace
import app.loococo.data.remote.manger.WorkspaceDataSource
import app.loococo.domain.model.Workspace
import app.loococo.domain.model.network.Resource
import app.loococo.domain.repository.WorkspaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WorkspaceRepositoryImpl @Inject constructor(
    private val workspaceDataSource: WorkspaceDataSource
) : WorkspaceRepository {

    override suspend fun findWorkspaceList(): Flow<Resource<List<Workspace>>> = flow {
        workspaceDataSource.findWorkspaceList().collect { result ->
            emit(
                when (result) {
                    is Resource.Success -> {
                        val user = result.data.map { it.toWorkspace() }
                        Resource.Success(user)
                    }

                    is Resource.Error -> result

                    is Resource.Message -> result
                }
            )
        }
    }
}