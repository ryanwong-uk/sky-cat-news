/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.repository

import io.ktor.client.plugins.HttpRequestTimeoutException
import java.net.ConnectException
import java.net.UnknownHostException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import uk.ryanwong.skycatnews.app.di.DispatcherModule
import uk.ryanwong.skycatnews.app.exception.RemoteSourceFailedWithNoCacheException
import uk.ryanwong.skycatnews.app.util.except
import uk.ryanwong.skycatnews.app.util.nicedateformatter.NiceDateFormatter
import uk.ryanwong.skycatnews.newslist.data.local.NewsListDao
import uk.ryanwong.skycatnews.newslist.data.local.entity.NewsItemEntity
import uk.ryanwong.skycatnews.newslist.data.local.entity.NewsListEntity
import uk.ryanwong.skycatnews.newslist.data.remote.NewsListService
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsListDto
import uk.ryanwong.skycatnews.newslist.domain.model.NewsItem
import uk.ryanwong.skycatnews.newslist.domain.model.NewsList

class NewsListRepositoryImpl(
    private val newsListService: NewsListService,
    private val newsListDao: NewsListDao,
    private val niceDateFormatter: NiceDateFormatter,
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
                                throw RemoteSourceFailedWithNoCacheException()
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

    override suspend fun getNewsItem(newsId: Int): Result<NewsItem?> {
        return withContext(dispatcher) {
            Result.runCatching {
                val newsItemEntity = newsListDao.getNewsItem(listId = listId, newsId = newsId)

                newsItemEntity?.let {
                    val newsItemList =
                        NewsItem.fromEntity(
                            newsItemEntities = listOf(newsItemEntity),
                            niceDateFormatter = niceDateFormatter,
                        )
                    newsItemList.getOrNull(0)
                }
            }
        }
    }

    private suspend fun getNewsListFromLocalDatabase(): NewsList {
        val newsItemEntities = newsListDao.getNewsList(listId = listId)
        val title = newsListDao.getNewsListTitle(listId = listId)

        return NewsList.fromEntity(
            title = title,
            newsItemEntities = newsItemEntities,
            niceDateFormatter = niceDateFormatter,
        )
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

        val newsItems = NewsItemEntity.fromDto(listId = listId, newsItemDtoList = newsListDto.news)
        newsListDao.insertNewsItems(newsItems = newsItems)
    }
}
