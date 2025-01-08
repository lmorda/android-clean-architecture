package com.lmorda.explore.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmorda.domain.DataRepository
import com.lmorda.explore.details.DetailsContract.State
import com.lmorda.utils.ShareIntentController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val SAVED_ID_KEY = "id"

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataRepository: DataRepository,
    private val shareIntentController: ShareIntentController,
) : ViewModel() {

    private val id: Long? = savedStateHandle[SAVED_ID_KEY]

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    init {
        id?.let { repoId ->
            viewModelScope.launch {
                try {
                    val githubRepo = dataRepository.getRepo(id = repoId)
                    val readmeContent = with (githubRepo) {
                        dataRepository.downloadTextFromUrl(
                            owner = owner.login,
                            name = name,
                            branch = defaultBranch,
                        )!!
                    }
                    _state.value = state.value.copy(
                        githubRepo = githubRepo,
                        readmeContent = readmeContent,
                    )
                } catch (ex: Exception) {
                    _state.value = state.value.copy(
                        exception = ex
                    )
                }
            }
        }
    }

    fun shareRepo() {
        shareIntentController.shareText(state.value.githubRepo?.name ?: "Ah shit son")
    }
}
