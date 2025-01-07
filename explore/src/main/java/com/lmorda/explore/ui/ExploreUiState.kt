package com.lmorda.explore.ui

import com.lmorda.domain.model.GithubRepo

interface ExploreContract {

    data class State(
        val isRefreshing: Boolean = false,
        val isFetchingNextPage: Boolean = false,
        val githubRepos: List<GithubRepo> = emptyList(),
        val pageNum: Int = 0,
        val exception: Throwable? = null,
    )
}
