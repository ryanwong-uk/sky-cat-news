/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.remote

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsListDTO

@OptIn(ExperimentalCoroutinesApi::class)
internal class NewsListServiceImplTest : FreeSpec() {

    private lateinit var scope: TestScope
    private lateinit var httpClient: HttpClient
    private lateinit var newsListService: NewsListService

    private fun setupDispatcher() {
        val dispatcher = StandardTestDispatcher()
        scope = TestScope(dispatcher)
    }

    private fun setupDataSource(status: HttpStatusCode, payload: String, contentType: String) {
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(payload),
                status = status,
                headers = headersOf(HttpHeaders.ContentType, contentType),
            )
        }

        httpClient = HttpClient(mockEngine) {
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
        newsListService = NewsListServiceImpl(httpClient = httpClient)
    }

    init {

        "getAllItems" - {
            "Should return NewsListDTO if API request is successful" {
                setupDispatcher()
                scope.runTest {
                    // Given
                    setupDataSource(
                        status = HttpStatusCode.OK,
                        contentType = "application/json",
                        payload = NewsListServiceTestData.mockJsonResponse,
                    )

                    // When
                    val newsListDTO = newsListService.getAllItems()

                    // Then
                    newsListDTO.isSuccess shouldBe true
                    newsListDTO.getOrNull() shouldBe NewsListServiceTestData.mockNewsListDTO
                }
            }

            "Should return an empty NewsListDTO object if API request is successful with empty body" {
                setupDispatcher()
                scope.runTest {
                    // Given
                    setupDataSource(
                        status = HttpStatusCode.OK,
                        contentType = "application/json",
                        payload = "",
                    )

                    // When
                    val newsListDTO = newsListService.getAllItems()

                    // Then
                    newsListDTO.isSuccess shouldBe true
                    newsListDTO.getOrNull() shouldBe NewsListDTO()
                }
            }

            "Should return null if API request returns HTTP Error" {
                setupDispatcher()
                scope.runTest {
                    // Given
                    setupDataSource(
                        status = HttpStatusCode.BadGateway,
                        contentType = "text/plain",
                        payload = "Bad Gateway",
                    )

                    // When
                    val newsListDTO = newsListService.getAllItems()

                    // Then
                    newsListDTO.isFailure shouldBe true
                    newsListDTO.exceptionOrNull() shouldBe Exception("Bad Gateway")
                }
            }
        }
    }
}
