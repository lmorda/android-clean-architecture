package com.lmorda.data.mapper

import com.lmorda.data.model.GithubRepoDto
import com.lmorda.data.model.GithubReposDto
import com.lmorda.domain.model.GithubRepo
import com.lmorda.domain.model.Owner
import javax.inject.Inject

class GithubRepoMapper @Inject constructor() {

    fun map(githubReposDto: GithubReposDto) =
        githubReposDto.items.map {
            map(githubRepoDto = it)
        }

    fun map(githubRepoDto: GithubRepoDto) =
        with(githubRepoDto) {
            GithubRepo(
                id = id,
                name = name ?: "",
                fullName = fullName ?: "",
                owner = Owner(
                    login = owner?.login ?: "",
                    avatarUrl = owner?.avatarUrl ?: "",
                    htmlUrl = owner?.htmlUrl ?: "",
                ),
                description = description ?: "",
                stargazersCount = countPrettyString(stargazersCount),
                language = language ?: "Informational",
            )
        }


    private fun countPrettyString(value: Int?): String {
        if (value == null) return ""
        return when {
            value >= 1_000_000 -> "${"%.1f".format(value / 1_000_000.0)}M"
            value >= 1_000 -> "${"%.1f".format(value / 1_000.0)}k"
            else -> value.toString()
        }
    }
}
