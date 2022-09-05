/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.domain.model

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import uk.ryanwong.skycatnews.newslist.data.local.entity.NewsItemEntity

internal class NewsItemTest : FreeSpec() {

    init {
        "fromEntity" - {
            "Should return an empty list if newsItemEntities is empty" {
                // Given
                val newsItemEntities = listOf<NewsItemEntity>()

                // When
                val newsItem = NewsItem.fromEntity(newsItemEntities = newsItemEntities)

                // then
                newsItem shouldBe emptyList()
            }

            "Should correctly convert a list with multiple newsItemEntities" {
                // Given
                val newsItemEntities = listOf(
                    NewsItemTestData.mockNewsItemEntity1,
                    NewsItemTestData.mockNewsItemEntity2,
                    NewsItemTestData.mockNewsItemEntity3
                )

                // When
                val newsItem = NewsItem.fromEntity(newsItemEntities = newsItemEntities)

                // then
                newsItem shouldContainInOrder listOf(
                    NewsItemTestData.mockNewsItem1,
                    NewsItemTestData.mockNewsItem2,
                    NewsItemTestData.mockNewsItem3
                )
            }

            "Should fill headline with empty string if it comes as null" {
                // Given
                val newsItemEntities = listOf(NewsItemTestData.mockNewsItemEntity1.copy(headline = null))

                // When
                val newsItem = NewsItem.fromEntity(newsItemEntities = newsItemEntities)

                // then
                newsItem shouldBe listOf(
                    NewsItem(
                        newsId = 1,
                        headline = "",
                        teaserText = "some-teaser-text",
                        modifiedDate = "2022-5-21T00:00:00Z",
                        teaserImageUrl = "https://some.teaser.image/href",
                        type = NewsType.STORY,
                        url = "https://some.url/"
                    )
                )
            }

            "Should fill teaserText with empty string if it comes as null" {
                // Given
                val newsItemEntities = listOf(NewsItemTestData.mockNewsItemEntity1.copy(teaserText = null))

                // When
                val newsItem = NewsItem.fromEntity(newsItemEntities = newsItemEntities)

                // then
                newsItem shouldBe listOf(
                    NewsItem(
                        newsId = 1,
                        headline = "some-headline",
                        teaserText = "",
                        modifiedDate = "2022-5-21T00:00:00Z",
                        teaserImageUrl = "https://some.teaser.image/href",
                        type = NewsType.STORY,
                        url = "https://some.url/"
                    )
                )
            }

            "Should fill teaserImageUrl with empty string if teaserImageHref comes as null" {
                // Given
                val newsItemEntities = listOf(NewsItemTestData.mockNewsItemEntity1.copy(teaserImageHref = null))

                // When
                val newsItem = NewsItem.fromEntity(newsItemEntities = newsItemEntities)

                // then
                newsItem shouldBe listOf(
                    NewsItem(
                        newsId = 1,
                        headline = "some-headline",
                        teaserText = "some-teaser-text",
                        modifiedDate = "2022-5-21T00:00:00Z",
                        teaserImageUrl = "",
                        type = NewsType.STORY,
                        url = "https://some.url/"
                    )
                )
            }

            "Should keep url as null if it comes as null" {
                // Given
                val newsItemEntities = listOf(NewsItemTestData.mockNewsItemEntity1.copy(url = null))

                // When
                val newsItem = NewsItem.fromEntity(newsItemEntities = newsItemEntities)

                // then
                newsItem shouldBe listOf(
                    NewsItem(
                        newsId = 1,
                        headline = "some-headline",
                        teaserText = "some-teaser-text",
                        modifiedDate = "2022-5-21T00:00:00Z",
                        teaserImageUrl = "https://some.teaser.image/href",
                        type = NewsType.STORY,
                        url = null
                    )
                )
            }
        }
    }
}
