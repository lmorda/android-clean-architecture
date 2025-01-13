package com.lmorda.data.model

val mockApiData = GithubReposDto(
    items = listOf(
        GithubRepoDto(
            id = 0,
            name = "my-application",
            fullName = "My Application",
            owner = OwnerDto("google", "", ""),
            description = "description for google my application",
            stargazersCount = 345123,
            language = "kotlin",
        ),
        GithubRepoDto(
            id = 1,
            name = "my-application-2",
            fullName = "My Application 2",
            owner = OwnerDto("google", "", ""),
            description = "description for google my application 2",
            stargazersCount = 345345,
            language = "kotlin",
        ),
    ),
)
