package com.lmorda.utils

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class ShareIntentControllerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ShareIntentController {

    override fun shareText(text: String) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    "Share via"
                ).apply {
                    flags = FLAG_ACTIVITY_NEW_TASK
                },
            )
        } catch (ex: Exception) {
          Timber.e(ex)
        }
    }
}
