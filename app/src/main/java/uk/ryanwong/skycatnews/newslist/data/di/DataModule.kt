/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uk.ryanwong.skycatnews.newslist.data.remote.FakeNewsListService
import uk.ryanwong.skycatnews.newslist.data.remote.NewsListService

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {
    @Provides
    @ViewModelScoped
    fun provideNewsListService(): NewsListService {
        // TODO: When we are ready to connect to a real backend, change below to NewsListServiceImpl
        // The DEFAULT_BASE_URL is defined in the app-level build.gradle
        return FakeNewsListService()
    }
}
