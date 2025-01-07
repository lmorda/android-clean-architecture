package com.lmorda.explore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lmorda.design.theme.Blue80
import com.lmorda.design.theme.DayAndNightPreview
import com.lmorda.design.theme.Green80
import com.lmorda.design.theme.HomeworkTheme
import com.lmorda.design.theme.Orange80
import com.lmorda.design.theme.Pink80
import com.lmorda.design.theme.Yellow80
import com.lmorda.design.theme.dimenMedium
import com.lmorda.design.theme.dimenMediumLarge
import com.lmorda.domain.model.GithubRepo
import com.lmorda.domain.model.mockDomainData

@Composable
internal fun GithubItemBottom(details: GithubRepo) {
    Row(
        modifier = Modifier
            .padding(vertical = dimenMedium)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(size = dimenMediumLarge),
            imageVector = Icons.Default.Star,
            tint = Yellow80,
            contentDescription = "star",
        )
        Text(
            modifier = Modifier.padding(horizontal = dimenMedium),
            text = details.stargazersCount,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
        )
        Icon(
            modifier = Modifier.size(size = dimenMediumLarge),
            imageVector = Icons.Default.PlayArrow,
            tint = getLanguageTintColor(details),
            contentDescription = "language",
        )
        Text(
            modifier = Modifier.padding(start = dimenMedium),
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

@DayAndNightPreview
@Composable
private fun GithubItemBottomPreview() {
    HomeworkTheme {
        GithubItemBottom(
            details = mockDomainData.first()
        )
    }
}