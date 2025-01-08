package com.lmorda.domain

import com.lmorda.domain.model.GithubRepo

interface DataRepository {

    suspend fun getRepos(page: Int): List<GithubRepo>

    suspend fun getRepo(id: Long): GithubRepo

    suspend fun downloadTextFromUrl(owner: String, name: String, branch: String): String?
}
