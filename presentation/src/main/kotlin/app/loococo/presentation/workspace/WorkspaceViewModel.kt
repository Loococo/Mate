package app.loococo.presentation.workspace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.loococo.domain.model.network.Resource
import app.loococo.domain.usecase.WorkspaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class WorkspaceViewModel @Inject constructor(
    private val workspaceUseCase: WorkspaceUseCase
) : ContainerHost<WorkspaceState, WorkspaceEffect>, ViewModel() {

    override val container =
        container<WorkspaceState, WorkspaceEffect>(WorkspaceState())

    init {
        findWorkspaceList()
    }

    private fun findWorkspaceList() = intent {
        reduce { state.copy(isLoading = true) }

        viewModelScope.launch {
            workspaceUseCase.findWorkspaceList().collect {
                reduce { state.copy(isLoading = false) }

                when(it) {
                    is Resource.Success -> {
                        reduce { state.copy(workspaceList = it.data) }

                    }
                    is Resource.Error -> {

                    }
                    is Resource.Message -> {

                    }
                }
            }
        }
    }
}