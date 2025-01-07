package com.lmorda.data

import com.lmorda.data.model.GithubRepoDto
import com.lmorda.data.model.GithubReposDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): GithubReposDto

    @GET("repositories/{id}")
    suspend fun getRepo(
        @Path("id") id: Long,
    ): GithubRepoDto
}
