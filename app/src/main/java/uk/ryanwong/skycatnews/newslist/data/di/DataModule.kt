/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.remote.FakeNewsListService
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.remote.NewsListService
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.remote.FakeStoryService
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.remote.StoryService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }
    }

    @Provides
    fun provideNewsListService(): NewsListService {
        return FakeNewsListService()
    }

    @Provides
    fun provideStoryService(): StoryService {
        return FakeStoryService()
    }
}
