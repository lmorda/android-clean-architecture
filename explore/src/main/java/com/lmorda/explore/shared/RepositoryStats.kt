package com.lmorda.explore.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lmorda.design.theme.Blue80
import com.lmorda.design.theme.Green80
import com.lmorda.design.theme.Orange80
import com.lmorda.design.theme.Pink80
import com.lmorda.design.theme.Yellow80
import com.lmorda.design.theme.mediumLargeSize
import com.lmorda.design.theme.mediumSize
import com.lmorda.domain.model.GithubRepo

@Composable
internal fun RepositoryStats(details: GithubRepo) {
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
        )
        Icon(
            modifier = Modifier.size(size = mediumLargeSize),
            imageVector = Icons.Default.PlayArrow,
            tint = getLanguageTintColor(language = details.language),
            contentDescription = "language",
        )
        Text(
            modifier = Modifier.padding(start = mediumSize),
            text = details.language,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
fun getLanguageTintColor(language: String): Color =
    language.takeIf { it.isNotBlank() }
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