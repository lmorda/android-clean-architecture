package com.lmorda.explore.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import com.lmorda.explore.R

@Composable
fun AvatarImage(
    modifier: Modifier,
    avatarUrl: String,
    size: Dp,
) {
    avatarUrl.takeIf { it.isNotBlank() }?.let {
        AsyncImage(
            modifier = modifier,
            model = it,
            placeholder = painterResource(id = R.drawable.ic_android_green_24dp),
            error = painterResource(id = R.drawable.ic_android_green_24dp),
            contentDescription = "avatar",
        )
    } ?: Image(
        modifier = Modifier.size(size = size),
        painter = painterResource(id = R.drawable.ic_android_green_24dp),
        contentDescription = "avatar",
    )
}
