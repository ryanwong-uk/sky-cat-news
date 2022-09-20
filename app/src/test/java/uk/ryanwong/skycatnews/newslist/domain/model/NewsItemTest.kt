/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.domain.model

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import uk.ryanwong.skycatnews.app.util.nicedateformatter.MockNiceDateFormatter
import uk.ryanwong.skycatnews.newslist.data.local.entity.NewsItemEntity

internal class NewsItemTest : FreeSpec() {

    private lateinit var niceDateFormatter: MockNiceDateFormatter

    private fun setupNiceDateFormatter() {
        niceDateFormatter = MockNiceDateFormatter()
    }

    init {
        "fromEntity" - {
            "Should return an empty list if newsItemEntities is empty" {
                // Given
                setupNiceDateFormatter()
                val newsItemEntities = listOf<NewsItemEntity>()

                // When
                val newsItem = NewsItem.fromEntity(
                    newsItemEntities = newsItemEntities,
                    niceDateFormatter = niceDateFormatter
                )

                // then
                newsItem shouldBe emptyList()
            }

            "Should convert and keep only known types from multiple newsItemEntities" {
                // Given
                setupNiceDateFormatter()
                val newsItemEntities = listOf(
                    NewsItemTestData.mockNewsItemEntity1,
                    NewsItemTestData.mockNewsItemEntity2,
                    NewsItemTestData.mockNewsItemEntity3
                )

                // When
                val newsItem = NewsItem.fromEntity(
                    newsItemEntities = newsItemEntities,
                    niceDateFormatter = niceDateFormatter
                )

                // then
                newsItem shouldContainInOrder listOf(
                    NewsItemTestData.mockNewsItemStory,
                    NewsItemTestData.mockNewsItemWebLink
                )
            }

            "Should fill headline with empty string if it comes as null" {
                // Given
                setupNiceDateFormatter()
                val newsItemEntities =
                    listOf(NewsItemTestData.mockNewsItemEntity1.copy(headline = null))

                // When
                val newsItem = NewsItem.fromEntity(
                    newsItemEntities = newsItemEntities,
                    niceDateFormatter = niceDateFormatter
                )

                // then
                newsItem shouldBe listOf(
                    NewsItem.Story(
                        newsId = 1,
                        headline = "",
                        teaserText = "some-teaser-text",
                        modifiedDate = "2022-05-21T00:00:00Z",
                        niceDate = "2 days ago",
                        teaserImageUrl = "https://some.teaser.image/href",
                        teaserImageAccessibilityText = "some-teaser-image-accessibility-text",
                    )
                )
            }

            "Should fill teaserText with empty string if it comes as null" {
                // Given
                setupNiceDateFormatter()
                val newsItemEntities =
                    listOf(NewsItemTestData.mockNewsItemEntity1.copy(teaserText = null))

                // When
                val newsItem = NewsItem.fromEntity(
                    newsItemEntities = newsItemEntities,
                    niceDateFormatter = niceDateFormatter
                )

                // then
                newsItem shouldBe listOf(
                    NewsItem.Story(
                        newsId = 1,
                        headline = "some-headline",
                        teaserText = "",
                        modifiedDate = "2022-05-21T00:00:00Z",
                        niceDate = "2 days ago",
                        teaserImageUrl = "https://some.teaser.image/href",
                        teaserImageAccessibilityText = "some-teaser-image-accessibility-text",
                    )
                )
            }

            "Should fill teaserImageUrl with empty string if teaserImageHref comes as null" {
                // Given
                setupNiceDateFormatter()
                val newsItemEntities =
                    listOf(NewsItemTestData.mockNewsItemEntity1.copy(teaserImageHref = null))

                // When
                val newsItem = NewsItem.fromEntity(
                    newsItemEntities = newsItemEntities,
                    niceDateFormatter = niceDateFormatter
                )

                // then
                newsItem shouldBe listOf(
                    NewsItem.Story(
                        newsId = 1,
                        headline = "some-headline",
                        teaserText = "some-teaser-text",
                        modifiedDate = "2022-05-21T00:00:00Z",
                        niceDate = "2 days ago",
                        teaserImageUrl = "",
                        teaserImageAccessibilityText = "some-teaser-image-accessibility-text",
                    )
                )
            }

            "Should keep url as null if it comes as null" {
                // Given
                setupNiceDateFormatter()
                val newsItemEntities =
                    listOf(NewsItemTestData.mockNewsItemEntity1.copy(advertUrl = null))

                // When
                val newsItem = NewsItem.fromEntity(
                    newsItemEntities = newsItemEntities,
                    niceDateFormatter = niceDateFormatter
                )

                // then
                newsItem shouldBe listOf(
                    NewsItem.Story(
                        newsId = 1,
                        headline = "some-headline",
                        teaserText = "some-teaser-text",
                        modifiedDate = "2022-05-21T00:00:00Z",
                        niceDate = "2 days ago",
                        teaserImageUrl = "https://some.teaser.image/href",
                        teaserImageAccessibilityText = "some-teaser-image-accessibility-text",
                    )
                )
            }
        }
    }
}
