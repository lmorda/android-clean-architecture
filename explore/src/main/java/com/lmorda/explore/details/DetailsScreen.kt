package com.lmorda.explore.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lmorda.design.theme.largeSize
import com.lmorda.design.theme.mediumSize
import com.lmorda.design.theme.standardSize
import com.lmorda.design.theme.topAppBarColors
import com.lmorda.explore.R
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun DetailsScreenRoute(
    viewModel: DetailsViewModel,
    id: Long = 31792824,
    onBack: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    DetailsScreen(
        state = state,
        onBack = onBack,
        onShare = { viewModel.shareRepo() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    state: DetailsContract.State,
    onBack: () -> Unit,
    onShare: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
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
                    IconButton(onClick = onShare) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding).fillMaxSize()
        ) {
            if (state.exception != null) {
                item { DetailsLoadingError() }
            } else {
                state.githubRepo?.let {
                    item { DetailsContent(readmeContent = state.readmeContent) }
                }
            }
        }
    }
}


@Composable
fun DetailsContent(readmeContent: String?) {
    Row(modifier = Modifier.padding(standardSize), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Readme",
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            modifier = Modifier.padding(start = mediumSize),
            text = "README",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )

    }
    MarkdownText(
        modifier = Modifier.padding(horizontal = largeSize),
        markdown = readmeContent!!)
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