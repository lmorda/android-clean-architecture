package com.lmorda.explore.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lmorda.design.theme.DayAndNightPreview
import com.lmorda.design.theme.HomeworkTheme
import com.lmorda.design.theme.PaginationEffect
import com.lmorda.design.theme.dimenDefault
import com.lmorda.design.theme.dimenLarge
import com.lmorda.design.theme.dimenMedium
import com.lmorda.design.theme.dimenSmall
import com.lmorda.design.theme.dimenXLarge
import com.lmorda.domain.model.GithubRepo
import com.lmorda.domain.model.mockDomainData
import com.lmorda.explore.R

@Composable
fun ExploreScreenRoute(
    viewModel: ExploreViewModel,
) {
    val state = viewModel.state.collectAsState().value
    ExploreScreen(
        state = state,
        onNextPage = {
            viewModel.getRepos()
         },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExploreScreen(
    state: ExploreContract.State,
    onNextPage: () -> Unit,
) {
    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.explore_title),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isRefreshing -> ExploreProgressIndicator()
                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState,
                ) {
                    items(state.githubRepos) { details ->
                        ExploreItem(details = details, onNavigateToDetails = {})
                    }
                    if (state.isFetchingNextPage) {
                        item {
                            ExploreNextPageIndicator()
                        }
                    }
                }
            }
            PaginationEffect(
                listState = listState,
                buffer = 5, // Load more when 5 items away from the end
                onLoadMore = onNextPage,
            )
        }
    }
}

@Composable
private fun ExploreProgressIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(width = dimenXLarge),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
private fun ExploreNextPageIndicator() {
    Box(
        modifier = Modifier.fillMaxSize().padding(dimenMedium),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(width = dimenLarge),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
private fun ExploreItem(details: GithubRepo, onNavigateToDetails: (Long) -> Unit) {
    Column(
        modifier = Modifier
            .padding(all = dimenDefault)
            .clickable {
                onNavigateToDetails(details.id)
            }
    ) {
        GithubItemTitle(details = details)

        Text(
            modifier = Modifier.padding(top = dimenSmall),
            text = details.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 3,
        )

        GithubItemBottom(details)
    }
    HorizontalDivider(
        color = MaterialTheme.colorScheme.outlineVariant,
        thickness = 1.dp,
    )
}

@Composable
@DayAndNightPreview
private fun ExploreScreenPreviewDay() {
    HomeworkTheme {
        ExploreScreen(
            state = ExploreContract.State(
                isRefreshing = false,
                isFetchingNextPage = false,
                githubRepos = mockDomainData,
                exception = null,
                pageNum = 1,
            ),
            onNextPage = {},
        )
    }
}
