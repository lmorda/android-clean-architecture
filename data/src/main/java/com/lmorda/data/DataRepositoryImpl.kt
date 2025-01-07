package com.lmorda.data

import com.lmorda.data.mapper.GithubRepoMapper
import com.lmorda.domain.DataRepository
import com.lmorda.domain.model.GithubRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: GithubRepoMapper,
) : DataRepository {

    override suspend fun getRepos(
        query: String,
        page: Int,
        perPage: Int,
    ): List<GithubRepo> {
        try {
            val repos = apiService.searchRepositories(
                query = query,
                page = page,
                perPage = perPage,
            )
            return mapper.map(repos)
        } catch (ex: Exception) {
            throw (ex)
        }
    }

    override suspend fun getRepo(id: Long): GithubRepo {
        try {
            val repo = apiService.getRepo(
                id = id,
            )
            return mapper.map(repo)
        } catch (ex: Exception) {
            throw (ex)
        }
    }
}
