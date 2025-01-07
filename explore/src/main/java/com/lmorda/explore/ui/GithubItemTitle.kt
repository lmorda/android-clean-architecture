package com.lmorda.explore.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.lmorda.design.theme.DayAndNightPreview
import com.lmorda.design.theme.HomeworkTheme
import com.lmorda.design.theme.dimenLarge
import com.lmorda.design.theme.dimenMedium
import com.lmorda.design.theme.dimenSmall
import com.lmorda.domain.model.GithubRepo
import com.lmorda.domain.model.mockDomainData
import com.lmorda.explore.R

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
internal fun GithubItemTitle(details: GithubRepo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            details.owner.avatarUrl.takeIf { it.isNotBlank() }?.let {
                GlideImage(
                    modifier = Modifier
                        .size(size = dimenLarge)
                        .clip(shape = CircleShape),
                    model = details.owner.avatarUrl,
                    contentDescription = "avatar",
                )
            } ?: Image(
                modifier = Modifier.size(size = dimenLarge),
                painter = painterResource(id = R.drawable.ic_android_green_24dp),
                contentDescription = "avatar",
            )
            Text(
                modifier = Modifier.padding(start = dimenMedium),
                text = details.owner.login,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
            )
        }
        Text(
            modifier = Modifier.padding(top = dimenSmall),
            text = details.name,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@DayAndNightPreview
@Composable
private fun GithubItemTitlePreview() {
    HomeworkTheme {
        GithubItemTitle(
            details = mockDomainData.first()
        )
    }
}