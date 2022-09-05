/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.repository

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import uk.ryanwong.skycatnews.app.exception.RemoteSourceFailedWithNoCacheException
import uk.ryanwong.skycatnews.newslist.data.local.MockNewsListDao
import uk.ryanwong.skycatnews.newslist.data.local.entity.NewsListEntity
import uk.ryanwong.skycatnews.newslist.data.remote.MockNewsListService
import uk.ryanwong.skycatnews.newslist.domain.model.NewsList
import java.net.ConnectException
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
internal class NewsListRepositoryImplTest : FreeSpec() {

    private lateinit var scope: TestScope
    private lateinit var newsListRepository: NewsListRepository
    private lateinit var mockNewsListService: MockNewsListService
    private lateinit var mockNewsListDao: MockNewsListDao

    private fun setupRepository() {

        val dispatcher = StandardTestDispatcher()
        scope = TestScope(dispatcher)

        mockNewsListService = MockNewsListService()
        mockNewsListDao = MockNewsListDao()
        newsListRepository = NewsListRepositoryImpl(
            newsListService = mockNewsListService,
            newsListDao = mockNewsListDao,
            dispatcher = dispatcher
        )
    }

    init {
        "getNewsList" - {
            "Remote data source returned success" - {
                "Should update local database" {
                    setupRepository()
                    scope.runTest {
                        // Given
                        val listId = 1
                        val newsListDto = NewsListRepositoryImplTestData.mockNewsListDto
                        mockNewsListService.mockGetAllItemsResponse = Result.success(newsListDto)

                        // When
                        newsListRepository.getNewsList()

                        // Then
                        mockNewsListDao.mockInsertNewsListTitleReceivedValue shouldBe NewsListEntity(
                            listId = listId,
                            title = "some-title"
                        )
                        mockNewsListDao.mockDeleteNewsItemsReceivedValue shouldBe listId
                        mockNewsListDao.mockInsertNewsItemsReceivedValue shouldBe listOf(
                            NewsListRepositoryImplTestData.getMockNewsItemEntity(listId = listId)
                        )
                    }
                }

                "Should return Result.Success<NewsList>" {
                    setupRepository()
                    scope.runTest {
                        // Given
                        val listId = 1

                        // Only to trigger a success response,
                        // actual data to be tested is from mockNewsListDao.mockGetNewsListResponse
                        val newsListDto = NewsListRepositoryImplTestData.mockNewsListDto

                        mockNewsListService.mockGetAllItemsResponse = Result.success(newsListDto)
                        mockNewsListDao.mockGetNewsListTitleResponse = "some-title"
                        mockNewsListDao.mockGetNewsListResponse =
                            listOf(NewsListRepositoryImplTestData.getMockNewsItemEntity(listId = listId))

                        // When
                        val newsList = newsListRepository.getNewsList()

                        // Then
                        newsList shouldBe Result.success(NewsListRepositoryImplTestData.mockNewsList)
                    }
                }
            }

            "Remote data source returned OK but with empty list" - {
                "Should return Result.Success<NewsList>" {
                    setupRepository()
                    scope.runTest {
                        // Given

                        // Only to trigger a success response,
                        // actual data to be tested is from mockNewsListDao.mockGetNewsListResponse
                        val newsListDto = NewsListRepositoryImplTestData.mockNewsListDto

                        mockNewsListService.mockGetAllItemsResponse = Result.success(newsListDto)
                        mockNewsListDao.mockGetNewsListTitleResponse = null
                        mockNewsListDao.mockGetNewsListResponse = listOf()

                        // When
                        val newsList = newsListRepository.getNewsList()

                        // Then
                        newsList shouldBe Result.success(
                            NewsList(
                                title = "",
                                newsItems = emptyList()
                            )
                        )
                    }
                }
            }

            "Remote data source returned failure" - {
                "Should rethrow all other unexpected exceptions" {
                    setupRepository()
                    scope.runTest {
                        // Given
                        mockNewsListService.mockGetAllItemsResponse =
                            Result.failure(exception = ClassNotFoundException())

                        // When
                        val newsList = newsListRepository.getNewsList()

                        // Then
                        newsList.isFailure shouldBe true
                        newsList.exceptionOrNull() shouldBe ClassNotFoundException()
                    }
                }

                "Repository contains cached data" - {
                    "Should return Result.Success with cached NewsList for UnknownHostException" {
                        setupRepository()
                        scope.runTest {
                            // Given
                            val listId = 1
                            mockNewsListDao.mockGetNewsListTitleResponse = "some-title"
                            mockNewsListDao.mockGetNewsListResponse =
                                listOf(NewsListRepositoryImplTestData.getMockNewsItemEntity(listId = listId))
                            mockNewsListService.mockGetAllItemsResponse =
                                Result.failure(exception = UnknownHostException())

                            // When
                            val newsList = newsListRepository.getNewsList()

                            // Then
                            newsList shouldBe Result.success(NewsListRepositoryImplTestData.mockNewsList)
                        }
                    }

                    "Should return Result.Success with cached NewsList for ConnectException" {
                        setupRepository()
                        scope.runTest {
                            // Given
                            val listId = 1
                            mockNewsListDao.mockGetNewsListTitleResponse = "some-title"
                            mockNewsListDao.mockGetNewsListResponse =
                                listOf(NewsListRepositoryImplTestData.getMockNewsItemEntity(listId = listId))
                            mockNewsListService.mockGetAllItemsResponse =
                                Result.failure(exception = ConnectException())

                            // When
                            val newsList = newsListRepository.getNewsList()

                            // Then
                            newsList shouldBe Result.success(NewsListRepositoryImplTestData.mockNewsList)
                        }
                    }

                    "Should return Result.Success with cached NewsList for HttpRequestTimeoutException" {
                        setupRepository()
                        scope.runTest {
                            // Given
                            val listId = 1
                            mockNewsListDao.mockGetNewsListTitleResponse = "some-title"
                            mockNewsListDao.mockGetNewsListResponse =
                                listOf(NewsListRepositoryImplTestData.getMockNewsItemEntity(listId = listId))
                            mockNewsListService.mockGetAllItemsResponse =
                                Result.failure(
                                    exception = HttpRequestTimeoutException(
                                        "some-url",
                                        1200L
                                    )
                                )

                            // When
                            val newsList = newsListRepository.getNewsList()

                            // Then
                            newsList shouldBe Result.success(NewsListRepositoryImplTestData.mockNewsList)
                        }
                    }
                }

                "Repository contains no cached data" - {
                    "Should return Result.failure with RemoteSourceFailedWithNoCacheException for UnknownHostException" {
                        setupRepository()
                        scope.runTest {
                            // Given
                            mockNewsListDao.mockGetNewsListTitleResponse = null
                            mockNewsListDao.mockGetNewsListResponse = listOf()
                            mockNewsListService.mockGetAllItemsResponse =
                                Result.failure(exception = UnknownHostException())

                            // When
                            val newsList = newsListRepository.getNewsList()

                            // Then
                            newsList.isFailure shouldBe true
                            newsList.exceptionOrNull() shouldBe RemoteSourceFailedWithNoCacheException()
                        }
                    }

                    "Should return Result.failure with RemoteSourceFailedWithNoCacheException for ConnectException" {
                        setupRepository()
                        scope.runTest {
                            // Given
                            mockNewsListDao.mockGetNewsListTitleResponse = null
                            mockNewsListDao.mockGetNewsListResponse = listOf()
                            mockNewsListService.mockGetAllItemsResponse =
                                Result.failure(exception = ConnectException())

                            // When
                            val newsList = newsListRepository.getNewsList()

                            // Then
                            newsList.isFailure shouldBe true
                            newsList.exceptionOrNull() shouldBe RemoteSourceFailedWithNoCacheException()
                        }
                    }

                    "Should return Result.failure with RemoteSourceFailedWithNoCacheException for HttpRequestTimeoutException" {
                        setupRepository()
                        scope.runTest {
                            // Given
                            mockNewsListDao.mockGetNewsListTitleResponse = null
                            mockNewsListDao.mockGetNewsListResponse = listOf()
                            mockNewsListService.mockGetAllItemsResponse =
                                Result.failure(
                                    exception = HttpRequestTimeoutException(
                                        "some-url",
                                        1200L
                                    )
                                )

                            // When
                            val newsList = newsListRepository.getNewsList()

                            // Then
                            newsList.isFailure shouldBe true
                            newsList.exceptionOrNull() shouldBe RemoteSourceFailedWithNoCacheException()
                        }
                    }
                }
            }
        }
    }
}
