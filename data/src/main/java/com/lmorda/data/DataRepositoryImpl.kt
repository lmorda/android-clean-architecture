package com.lmorda.data

import com.lmorda.data.mapper.GithubRepoMapper
import com.lmorda.domain.DataRepository
import com.lmorda.domain.model.GithubRepo
import javax.inject.Inject
import javax.inject.Singleton

const val PER_PAGE = 30
const val SORT = "stars"
const val ORDER = "desc"
const val QUERY = "android"

@Singleton
class DataRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: GithubRepoMapper,
) : DataRepository {

    override suspend fun getRepos(page: Int): List<GithubRepo> {
        try {
            val repos = apiService.searchRepositories(
                page = page,
                perPage = PER_PAGE,
                query = QUERY,
                order = ORDER,
                sort = SORT,
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
