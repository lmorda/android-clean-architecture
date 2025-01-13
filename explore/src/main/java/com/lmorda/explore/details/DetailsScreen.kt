package com.lmorda.explore.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lmorda.design.theme.DayAndNightPreview
import com.lmorda.design.theme.HomeworkTheme
import com.lmorda.design.theme.mediumLargeSize
import com.lmorda.design.theme.mediumSize
import com.lmorda.design.theme.standardSize
import com.lmorda.design.theme.topAppBarColors
import com.lmorda.design.theme.xLargeSize
import com.lmorda.domain.model.GithubRepo
import com.lmorda.domain.model.mockDomainData
import com.lmorda.explore.R
import com.lmorda.explore.shared.AvatarImage
import com.lmorda.explore.shared.RepositoryStats
import com.lmorda.utils.shareText

@Composable
fun DetailsScreenRoute(
    viewModel: DetailsViewModel,
    onBack: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    DetailsScreen(
        state = state,
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    state: DetailsContract.State,
    onBack: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold(topBar = {
        TopAppBar(
            colors = topAppBarColors(),
            title = { },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            },
            actions = {
                val context = LocalContext.current
                val shareText =
                    state.githubRepo?.owner?.htmlUrl ?: stringResource(R.string.share_default)
                IconButton(onClick = { context.shareText(text = shareText) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            },
            scrollBehavior = scrollBehavior,
        )
    }) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(vertical = mediumSize, horizontal = mediumLargeSize)
                .fillMaxSize()
        ) {
            when {
                state.exception != null -> DetailsLoadingError()

                state.githubRepo != null -> DetailsContent(details = state.githubRepo)
            }
        }
    }
}

@Composable
fun ColumnScope.DetailsContent(details: GithubRepo) {
    AvatarImage(
        modifier = Modifier
            .align(alignment = Alignment.CenterHorizontally)
            .size(size = xLargeSize)
            .clip(shape = CircleShape),
        avatarUrl = details.owner.avatarUrl,
        size = xLargeSize,
    )
    Text(
        text = details.owner.login,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 1,
    )
    Text(
        text = details.name,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 1,
    )
    Text(
        modifier = Modifier.padding(top = standardSize),
        text = details.description,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
    )
    RepositoryStats(details = details)
}

@Composable
private fun DetailsLoadingError() {
    Box(modifier = Modifier.padding(standardSize)) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.loading_error)
        )
        val progress by animateLottieCompositionAsState(
            composition = composition
        )
        Text(
            text = stringResource(id = R.string.details_error),
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
@DayAndNightPreview
private fun DetailsScreenPreview() {
    HomeworkTheme {
        DetailsScreen(
            state = DetailsContract.State(
                githubRepo = mockDomainData[0],
            ),
            onBack = {},
        )
    }
}
