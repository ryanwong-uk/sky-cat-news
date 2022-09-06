/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.HttpClient
import uk.ryanwong.skycatnews.storydetail.data.remote.StoryService
import uk.ryanwong.skycatnews.storydetail.data.remote.StoryServiceImpl

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {
    @Provides
    @ViewModelScoped
    fun provideStoryService(httpClient: HttpClient): StoryService {
        return StoryServiceImpl(httpClient = httpClient)
    }
}
