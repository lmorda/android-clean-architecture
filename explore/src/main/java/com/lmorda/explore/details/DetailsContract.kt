package com.lmorda.explore.details

import com.lmorda.domain.model.GithubRepo

sealed interface DetailsContract {

    data class State(
        val githubRepo: GithubRepo? = null,
        val exception: Exception? = null,
    )

}
