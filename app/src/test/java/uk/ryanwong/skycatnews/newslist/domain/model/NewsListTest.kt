/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.domain.model

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import uk.ryanwong.skycatnews.app.util.nicedateformatter.MockNiceDateFormatter
import uk.ryanwong.skycatnews.newslist.data.local.entity.NewsItemEntity

internal class NewsListTest : FreeSpec() {

    lateinit var niceDateFormatter: MockNiceDateFormatter

    private fun setupNiceDateFormatter() {
        niceDateFormatter = MockNiceDateFormatter()
    }

    init {
        "isEmpty" - {
            "Should return true if it contains title but no newsItems" {
                // Given
                val newsList = NewsList(title = "some-title", newsItems = emptyList())

                // When
                val isEmpty = newsList.isEmpty()

                // Then
                isEmpty shouldBe true
            }

            "Should return false if newsItems is not empty" {
                // Given
                val newsList = NewsList(
                    title = "some-title",
                    newsItems = listOf(NewsListTestData.mockNewsItemStory)
                )

                // When
                val isEmpty = newsList.isEmpty()

                // Then
                isEmpty shouldBe false
            }
        }

        "fromEntity" - {
            "Should fill title with empty string if it comes as null" {
                // Given
                setupNiceDateFormatter()
                val title = null
                val newsItemEntities = emptyList<NewsItemEntity>()

                // When
                val newsList = NewsList.fromEntity(
                    title = title,
                    newsItemEntities = newsItemEntities,
                    niceDateFormatter = niceDateFormatter
                )

                // Then
                newsList shouldBe NewsList(title = "", newsItems = emptyList())
            }

            "Should return NewsList correctly if newsItemEntities contains one item" {
                // Given
                setupNiceDateFormatter()
                val title = "some-title"
                val newsItemEntities = listOf(NewsListTestData.mockNewsItemEntityStory)

                // When
                val newsList = NewsList.fromEntity(
                    title = title,
                    newsItemEntities = newsItemEntities,
                    niceDateFormatter = niceDateFormatter
                )

                // Then
                newsList shouldBe NewsList(
                    title = "some-title",
                    newsItems = listOf(NewsListTestData.mockNewsItemStory)
                )
            }

            "Should convert and keep only known types from multiple newsItemEntities" {
                // Given
                setupNiceDateFormatter()
                val title = "some-title"
                val newsItemEntities = listOf(
                    NewsListTestData.mockNewsItemEntityStory,
                    NewsListTestData.mockNewsItemEntityWebLink,
                    NewsListTestData.mockNewsItemEntityUnknown
                )

                // When
                val newsList = NewsList.fromEntity(
                    title = title,
                    newsItemEntities = newsItemEntities,
                    niceDateFormatter = niceDateFormatter
                )

                // Then
                newsList shouldBe NewsList(
                    title = "some-title",
                    newsItems = listOf(
                        NewsListTestData.mockNewsItemStory,
                        NewsListTestData.mockNewsItemWebLink
                    )
                )
            }
        }
    }
}
