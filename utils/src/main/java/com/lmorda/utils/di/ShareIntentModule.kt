package com.lmorda.utils.di

import com.lmorda.utils.ShareIntentController
import com.lmorda.utils.ShareIntentControllerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ShareIntentModule {
    @Binds
    abstract fun bindsShareIntentController(impl: ShareIntentControllerImpl): ShareIntentController
}
