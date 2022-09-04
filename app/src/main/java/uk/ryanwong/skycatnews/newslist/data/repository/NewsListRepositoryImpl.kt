/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.repository

import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uk.ryanwong.skycatnews.except
import uk.ryanwong.skycatnews.newslist.data.local.NewsListDao
import uk.ryanwong.skycatnews.newslist.data.local.model.NewsItemEntity
import uk.ryanwong.skycatnews.newslist.data.local.model.NewsListEntity
import uk.ryanwong.skycatnews.newslist.data.remote.NewsListService
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsListDTO
import uk.ryanwong.skycatnews.newslist.data.repository.NewsListRepository
import uk.ryanwong.skycatnews.newslist.domain.model.NewsItem
import java.net.ConnectException
import java.net.UnknownHostException

class NewsListRepositoryImpl(
    private val newsListService: NewsListService,
    private val newsListDao: NewsListDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : NewsListRepository {

    // For now Sky Cat News is good enough to always assign listId = 1
    // Hardcoded until API returns more than one lists that comes with their own Ids
    private val listId = 1

    override suspend fun getNewsList(): Result<List<NewsItem>> {
        return withContext(dispatcher) {

            val networkResult = newsListService.getAllItems().onSuccess { newsListDTO ->
                updateLocalDatabase(newsListDTO = newsListDTO)
            }

            val newsList =
                NewsItem.fromEntity(newsItemEntities = newsListDao.getNewsList(listId = listId))

            Result.runCatching {
                if (networkResult.isSuccess) {
                    newsList
                } else {
                    networkResult.exceptionOrNull()?.let { exception ->
                        when (exception) {
                            is UnknownHostException,
                            is ConnectException,
                            is HttpRequestTimeoutException,
                            -> {
                                if (newsList.isNotEmpty()) {
                                    newsList
                                } else {
                                    throw Exception("Error receiving data from server. No cached content available.")
                                }
                            }
                            else -> {
                                throw exception
                            }
                        }
                    } ?: newsList
                }
            }.except<CancellationException, _>()
        }
    }

    override suspend fun getNewsListTitle(): String {
        return newsListDao.getNewsListTitle(listId = listId)
    }

    private suspend fun updateLocalDatabase(newsListDTO: NewsListDTO) {
        newsListDao.insertNewsListTitle(
            newsListEntity = NewsListEntity(
                listId = listId,
                title = newsListDTO.title ?: "",
            )
        )

        // Cleaning up DB first, as we are not using paging and the API behaviour is unknown
        newsListDao.deleteNewsItems(listId = listId)

        NewsItemEntity.fromDTO(listId = listId, newsItemDTO = newsListDTO.news)?.let { newsItems ->
            newsListDao.insertNewsItems(
                newsItems = newsItems,
            )
        }
    }
}
