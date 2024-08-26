package app.loococo.data.repository

import android.util.Log
import app.loococo.data.model.request.WorkspaceRequest
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

    private fun validateCredentials(name: String, slug: String): Resource.Message? {
        return when {
            name.isBlank() || slug.isBlank() -> Resource.Message("Please enter your workspace info")
            else -> null
        }
    }

    override suspend fun create(name: String, slug: String): Flow<Resource<Unit>> = flow {
        Log.e("---------------1", "$name--$slug")
        val validationError = validateCredentials(name, slug)
        if (validationError != null) {
            emit(validationError)
            return@flow
        }

        Log.e("---------------2", "$name--$slug")
        workspaceDataSource.create(WorkspaceRequest(name, slug)).collect { result ->
            emit(
                when (result) {
                    is Resource.Success -> {
                        Resource.Success(Unit)
                    }

                    is Resource.Error -> result

                    is Resource.Message -> result
                }
            )
        }
    }
}