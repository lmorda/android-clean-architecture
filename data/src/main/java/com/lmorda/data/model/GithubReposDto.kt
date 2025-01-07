package com.lmorda.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubReposDto(
    @SerialName("items") val items: List<GithubRepoDto>,
)
