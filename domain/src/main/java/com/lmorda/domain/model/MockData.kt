package com.lmorda.domain.model

val mockDomainData = listOf(
    GithubRepo(
        id = 0,
        name = "my-application",
        fullName = "My Application",
        owner = Owner("google", "", ""),
        description = "description for google my application",
        stargazersCount = "3.45k",
        language = "kotlin",
    ),
    GithubRepo(
        id = 1,
        name = "my-application-2",
        fullName = "My Application 2",
        owner = Owner("google", "", ""),
        description = "description for google my application 2",
        stargazersCount = "3.45k",
        language = "kotlin",
    ),
)
