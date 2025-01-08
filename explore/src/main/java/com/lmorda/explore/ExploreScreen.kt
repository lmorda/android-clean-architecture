package com.lmorda.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lmorda.design.theme.Blue80
import com.lmorda.design.theme.DayAndNightPreview
import com.lmorda.design.theme.Green80
import com.lmorda.design.theme.HomeworkTheme
import com.lmorda.design.theme.Orange80
import com.lmorda.design.theme.PaginationEffect
import com.lmorda.design.theme.Pink80
import com.lmorda.design.theme.Yellow80
import com.lmorda.design.theme.standardSize
import com.lmorda.design.theme.largeSize
import com.lmorda.design.theme.mediumLargeSize
import com.lmorda.design.theme.mediumSize
import com.lmorda.design.theme.smallSize
import com.lmorda.design.theme.topAppBarColors
import com.lmorda.domain.model.GithubRepo
import com.lmorda.domain.model.mockDomainData

@Composable
fun ExploreScreenRoute(
    viewModel: ExploreViewModel,
) {
    val state = viewModel.state.collectAsState().value
    ExploreScreen(
        state = state,
        onNextPage = viewModel::getNextPage,
        onRefresh = viewModel::onRefresh
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExploreScreen(
    state: ExploreContract.State,
    onNextPage: () -> Unit,
    onRefresh: () -> Unit,
) {
    val listState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(),
                title = {
                    ExploreTitle()
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ExploreContent(state, listState, onNextPage)
        }
    }
}

@Composable
private fun ExploreTitle() {
    Text(
        text = stringResource(id = R.string.explore_title),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}

@Composable
private fun ExploreContent(
    state: ExploreContract.State,
    listState: LazyListState,
    onNextPage: () -> Unit
) {
    when {
        state.exception != null -> ExploreLoadingError()
        state.isFirstLoad -> ExploreProgressIndicator()
        else -> ExploreList(listState, state)
    }
    if (!state.isLoading()) {
        PaginationEffect(
            listState = listState,
            buffer = 5, // Load more when 5 items away from the end
            onLoadMore = onNextPage,
        )
    }
}

@Composable
private fun ExploreList(
    listState: LazyListState,
    state: ExploreContract.State
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
    ) {
        items(state.githubRepos) { details ->
            ExploreItem(details = details, onNavigateToDetails = {})
        }
        if (state.isFetchingNextPage) {
            item { ExploreNextPageIndicator() }
        }
    }
}

@Composable
private fun ExploreProgressIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.loading)
        )
        val progress by animateLottieCompositionAsState(
            composition = composition
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }
}

@Composable
private fun ExploreNextPageIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(mediumSize),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(width = largeSize),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
private fun ExploreLoadingError() {
    Box(modifier = Modifier.padding(standardSize)) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.loading_error)
        )
        val progress by animateLottieCompositionAsState(
            composition = composition
        )
        Text(
            text = stringResource(id = R.string.error_message),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }
}

@Composable
private fun ExploreItem(details: GithubRepo, onNavigateToDetails: (Long) -> Unit) {
    Column(
        modifier = Modifier
            .padding(all = standardSize)
            .clickable {
                onNavigateToDetails(details.id)
            }
    ) {
        RepositoryTitle(details = details)

        Text(
            modifier = Modifier.padding(top = smallSize),
            text = details.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 3,
        )

        RepositoryStats(details)
    }
    HorizontalDivider(
        color = MaterialTheme.colorScheme.outlineVariant,
        thickness = 1.dp,
    )
}

@Composable
private fun RepositoryTitle(details: GithubRepo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OwnerImage(avatarUrl = details.owner.avatarUrl)
            Text(
                modifier = Modifier.padding(start = mediumSize),
                text = details.owner.login,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
            )
        }
        Text(
            modifier = Modifier.padding(top = smallSize),
            text = details.name,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun OwnerImage(
    avatarUrl: String?,
) {
    avatarUrl?.takeIf { it.isNotBlank() }?.let {
        GlideImage(
            modifier = Modifier
                .size(size = largeSize)
                .clip(shape = CircleShape),
            model = avatarUrl,
            contentDescription = "avatar",
        )
    } ?: Image(
        modifier = Modifier.size(size = largeSize),
        painter = painterResource(id = R.drawable.ic_android_green_24dp),
        contentDescription = "avatar",
    )
}

@Composable
private fun RepositoryStats(details: GithubRepo) {
    Row(
        modifier = Modifier
            .padding(vertical = mediumSize)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(size = mediumLargeSize),
            imageVector = Icons.Default.Star,
            tint = Yellow80,
            contentDescription = "star",
        )
        Text(
            modifier = Modifier.padding(horizontal = mediumSize),
            text = details.stargazersCount,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
        )
        Icon(
            modifier = Modifier.size(size = mediumLargeSize),
            imageVector = Icons.Default.PlayArrow,
            tint = getLanguageTintColor(details),
            contentDescription = "language",
        )
        Text(
            modifier = Modifier.padding(start = mediumSize),
            text = details.language,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
        )
    }
}

@Composable
private fun getLanguageTintColor(details: GithubRepo) = details.language
    .takeIf { it.isNotBlank() }
    ?.firstOrNull()
    ?.lowercaseChar()
    ?.let { char ->
        when (char) {
            in 'a'..'e' -> Pink80
            in 'f'..'i' -> Orange80
            in 'j'..'p' -> Green80
            in 'q'..'z' -> Blue80
            else -> Pink80
        }
    } ?: Pink80


@Composable
@DayAndNightPreview
private fun ExploreScreenPreview() {
    HomeworkTheme {
        ExploreScreen(
            state = ExploreContract.State(
                githubRepos = mockDomainData,
            ),
            onNextPage = {},
            onRefresh = {},
        )
    }
}