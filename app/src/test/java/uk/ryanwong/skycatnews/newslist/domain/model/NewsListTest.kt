/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.domain.model

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import uk.ryanwong.skycatnews.newslist.data.local.entity.NewsItemEntity

internal class NewsListTest : FreeSpec() {

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
                    newsItems = listOf(NewsListTestData.mockNewsItem1)
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
                val title = null
                val newsItemEntities = emptyList<NewsItemEntity>()

                // When
                val newsList = NewsList.fromEntity(
                    title = title,
                    newsItemEntities = newsItemEntities
                )

                // Then
                newsList shouldBe NewsList(title = "", newsItems = emptyList())
            }

            "Should return NewsList correctly if newsItemEntities contains one item" {
                // Given
                val title = "some-title"
                val newsItemEntities = listOf(NewsListTestData.mockNewsItemEntity1)

                // When
                val newsList = NewsList.fromEntity(
                    title = title,
                    newsItemEntities = newsItemEntities
                )

                // Then
                newsList shouldBe NewsList(
                    title = "some-title",
                    newsItems = listOf(NewsListTestData.mockNewsItem1)
                )
            }

            "Should return NewsList correctly if newsItemEntities contains multiple items" {
                // Given
                val title = "some-title"
                val newsItemEntities = listOf(
                    NewsListTestData.mockNewsItemEntity1,
                    NewsListTestData.mockNewsItemEntity2,
                    NewsListTestData.mockNewsItemEntity3
                )

                // When
                val newsList = NewsList.fromEntity(
                    title = title,
                    newsItemEntities = newsItemEntities
                )

                // Then
                newsList shouldBe NewsList(
                    title = "some-title",
                    newsItems = listOf(
                        NewsListTestData.mockNewsItem1,
                        NewsListTestData.mockNewsItem2,
                        NewsListTestData.mockNewsItem3
                    )
                )
            }
        }
    }
}
