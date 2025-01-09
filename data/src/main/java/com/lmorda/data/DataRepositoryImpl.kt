package com.lmorda.data

import com.lmorda.data.mapper.GithubRepoMapper
import com.lmorda.domain.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: GithubRepoMapper,
) : DataRepository {

    override suspend fun getRepos(page: Int) =
        mapper.map(
            githubReposDto = apiService.searchRepositories(
                page = page,
                perPage = 30,
                query = "android",
                order = "desc",
                sort = "stars",
            )
        )

    override suspend fun getRepo(id: Long) =
        mapper.map(
            githubRepoDto = apiService.getRepo(id = id)
        )

    override suspend fun downloadTextFromUrl(owner: String, name: String, branch: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                val url = "https://raw.githubusercontent.com/$owner/$name/refs/heads/$branch/README.md"
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.inputStream.bufferedReader().use { it.readText() }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

}
