package com.lmorda.explore.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lmorda.design.theme.largeSize
import com.lmorda.design.theme.topAppBarColors
import com.lmorda.domain.model.GithubRepo
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
            state.githubRepo?.let {
                item { DetailsContent(githubRepo = it, readmeContent = state.readmeContent) }
            }
        }
    }
}


@Composable
fun DetailsContent(githubRepo: GithubRepo, readmeContent: String?) {
    MarkdownText(
        modifier = Modifier.padding(horizontal = largeSize),
        markdown = readmeContent!!)
}
