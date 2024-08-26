package app.loococo.presentation.workspace

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.loococo.domain.model.network.Resource
import app.loococo.domain.usecase.WorkspaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
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

    fun handleIntent(intent: WorkspaceIntent) {
        when (intent) {
            WorkspaceIntent.WorkspaceCreateClicked -> updatePopupState()
            WorkspaceIntent.WorkspaceCreateConfirmed -> create()
            is WorkspaceIntent.WorkspaceNameChanged -> updateName(intent.name)
            is WorkspaceIntent.WorkspaceSlugChanged -> updateSlug(intent.slug)
        }
    }

    private fun findWorkspaceList() = intent {
        reduce { state.copy(isLoading = true) }

        viewModelScope.launch {
            workspaceUseCase.findWorkspaceList().collect {
                reduce { state.copy(isLoading = false) }

                when (it) {
                    is Resource.Success -> {
                        reduce { state.copy(workspaceList = it.data) }
                    }

                    is Resource.Error -> {
                        postSideEffect(WorkspaceEffect.ShowToast("error"))

                    }

                    is Resource.Message -> {
                        postSideEffect(WorkspaceEffect.ShowToast(it.message))

                    }
                }
            }
        }
    }

    private fun updatePopupState() = intent {
        reduce { state.copy(showPopup = !state.showPopup) }
    }

    private fun updateName(name: TextFieldValue) = intent {
        reduce { state.copy(workspaceName = name) }
    }

    private fun updateSlug(slug: TextFieldValue) = intent {
        reduce { state.copy(workspaceSlug = slug) }
    }

    private fun create() = intent {
        reduce { state.copy(isLoading = true) }
        viewModelScope.launch {
            workspaceUseCase.create(state.workspaceName.text, state.workspaceSlug.text).collect {
                reduce { state.copy(isLoading = false) }

                when (it) {
                    is Resource.Success -> {
                        reduce { state.copy(showPopup = !state.showPopup) }
                        postSideEffect(WorkspaceEffect.NavigationHome)
                    }

                    is Resource.Error -> {
                        postSideEffect(WorkspaceEffect.ShowToast("error"))
                    }

                    is Resource.Message -> {
                        postSideEffect(WorkspaceEffect.ShowToast(it.message))
                    }
                }
            }
        }
    }
}