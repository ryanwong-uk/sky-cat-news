/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.repository

import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import uk.ryanwong.skycatnews.app.di.DispatcherModule
import uk.ryanwong.skycatnews.except
import uk.ryanwong.skycatnews.newslist.data.local.NewsListDao
import uk.ryanwong.skycatnews.newslist.data.local.model.NewsItemEntity
import uk.ryanwong.skycatnews.newslist.data.local.model.NewsListEntity
import uk.ryanwong.skycatnews.newslist.data.remote.NewsListService
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsListDto
import uk.ryanwong.skycatnews.newslist.domain.model.NewsList
import java.net.ConnectException
import java.net.UnknownHostException

class NewsListRepositoryImpl(
    private val newsListService: NewsListService,
    private val newsListDao: NewsListDao,
    @DispatcherModule.IoDispatcher private val dispatcher: CoroutineDispatcher,
) : NewsListRepository {

    // For now Sky Cat News is good enough to always assign listId = 1
    // Hardcoded until API returns more than one lists that comes with their own Ids
    private val listId = 1

    override suspend fun getNewsList(): Result<NewsList> {
        return withContext(dispatcher) {
            Result.runCatching {
                val networkResult = newsListService.getAllItems()

                if (networkResult.isFailure) {
                    val throwable = networkResult.exceptionOrNull()
                    when (throwable) {
                        null, // should not happen
                        is UnknownHostException,
                        is ConnectException,
                        is HttpRequestTimeoutException,
                        -> {
                            val newsList = getNewsListFromLocalDatabase()
                            if (newsList.isEmpty()) {
                                throw Exception("Error receiving data from server. No cached content available.")
                            } else {
                                return@runCatching newsList
                            }
                        }
                        else -> {
                            throw throwable
                        }
                    }
                } else {
                    networkResult.getOrNull()?.let { newsListDto ->
                        updateLocalDatabase(newsListDto = newsListDto)
                    }
                    return@runCatching getNewsListFromLocalDatabase()
                }
            }.except<CancellationException, _>()
        }
    }

    private suspend fun getNewsListFromLocalDatabase(): NewsList {
        val newsItemEntities = newsListDao.getNewsList(listId = listId)
        val title = newsListDao.getNewsListTitle(listId = listId)

        return NewsList.fromEntity(title = title, newsItemEntities = newsItemEntities)
    }

    private suspend fun updateLocalDatabase(newsListDto: NewsListDto) {
        newsListDao.insertNewsListTitle(
            newsListEntity = NewsListEntity(
                listId = listId,
                title = newsListDto.title,
            )
        )

        // Cleaning up DB first, as we are not using paging and the API behaviour is not clearly defined
        newsListDao.deleteNewsItems(listId = listId)

        NewsItemEntity.fromDto(listId = listId, newsItemDto = newsListDto.news)?.let { newsItems ->
            newsListDao.insertNewsItems(
                newsItems = newsItems,
            )
        }
    }
}
