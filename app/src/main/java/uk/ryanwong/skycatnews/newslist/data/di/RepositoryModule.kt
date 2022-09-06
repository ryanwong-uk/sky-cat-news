/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import uk.ryanwong.skycatnews.app.di.DispatcherModule
import uk.ryanwong.skycatnews.newslist.data.local.NewsListDao
import uk.ryanwong.skycatnews.newslist.data.remote.NewsListService
import uk.ryanwong.skycatnews.newslist.data.repository.NewsListRepository
import uk.ryanwong.skycatnews.newslist.data.repository.NewsListRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideNewsListRepository(
        newsListService: NewsListService,
        newsListDao: NewsListDao,
        @DispatcherModule.IoDispatcher dispatcher: CoroutineDispatcher,
    ): NewsListRepository {
        return NewsListRepositoryImpl(
            newsListService = newsListService,
            newsListDao = newsListDao,
            dispatcher = dispatcher
        )
    }
}
