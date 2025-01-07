package com.lmorda.domain

import com.lmorda.domain.model.GithubRepo

interface DataRepository {

    suspend fun getRepos(query: String, page: Int, perPage: Int): List<GithubRepo>

    suspend fun getRepo(id: Long): GithubRepo

}
