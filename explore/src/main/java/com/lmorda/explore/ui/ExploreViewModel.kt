package com.lmorda.explore.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmorda.domain.DataRepository
import com.lmorda.explore.ui.ExploreContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    fun getRepos() {
        viewModelScope.launch {
            if (state.value.pageNum == 0) {
                _state.update { it.copy(isRefreshing = true) }
            }
            try {
                val repos = dataRepository.getRepos(
                    query = "google",
                    page = state.value.pageNum + 1,
                    perPage = 30,
                )
                _state.update {
                    it.copy(
                        isRefreshing = false,
                        githubRepos = it.githubRepos + repos, // Append new items
                        pageNum = state.value.pageNum + 1, // Update the page number
                        isFetchingNextPage = true,
                    )
                }
            } catch (ex: Exception) {
                _state.update { it.copy(isRefreshing = false, exception = ex) }
            }
        }
    }
}
