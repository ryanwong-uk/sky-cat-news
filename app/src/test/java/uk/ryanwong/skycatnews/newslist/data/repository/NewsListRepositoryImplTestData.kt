/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.repository

import uk.ryanwong.skycatnews.newslist.data.local.model.NewsItemEntity
import uk.ryanwong.skycatnews.newslist.data.remote.model.LinksDto
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsItemDto
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsListDto
import uk.ryanwong.skycatnews.newslist.data.remote.model.TeaserImageDto
import uk.ryanwong.skycatnews.newslist.data.remote.model.UrlDto
import uk.ryanwong.skycatnews.newslist.domain.model.NewsItem
import uk.ryanwong.skycatnews.newslist.domain.model.NewsList
import uk.ryanwong.skycatnews.newslist.domain.model.NewsType

internal object NewsListRepositoryImplTestData {
    val mockNewsItemDto = NewsItemDto(
        creationDate = "2020-11-18T00:00:00Z",
        headline = "some-headline",
        id = 1,
        modifiedDate = "2020-11-19T00:00:00Z",
        teaserImage = TeaserImageDto(
            links = LinksDto(
                url = UrlDto(
                    href = "https://some.url/href",
                    templated = true,
                    type = "image/jpeg"
                )
            ),
            accessibilityText = "some-accessibility-text"
        ),
        teaserText = "some-teaser-text",
        type = "story",
        url = null,
        weblinkUrl = null
    )

    val mockNewsListDto = NewsListDto(
        title = "some-title",
        news = listOf(mockNewsItemDto)
    )

    fun getMockNewsItemEntity(listId: Int) = NewsItemEntity(
        listId = listId,
        newsId = 1,
        type = "story",
        headline = "some-headline",
        creationDate = "2020-11-18T00:00:00Z",
        modifiedDate = "2020-11-19T00:00:00Z",
        url = null,
        weblinkUrl = null,
        teaserText = "some-teaser-text",
        teaserImageHref = "https://some.url/href",
        teaserImageTemplated = true,
        teaserImageType = "image/jpeg",
        teaserImageAccessibilityText = "some-accessibility-text"
    )

    val mockNewsList = NewsList(
        title = "some-title",
        newsItems = listOf(
            NewsItem(
                newsId = 1,
                headline = "some-headline",
                teaserText = "some-teaser-text",
                modifiedDate = "2020-11-19T00:00:00Z",
                teaserImageUrl = "https://some.url/href",
                type = NewsType.UNKNOWN,
                url = null
            )
        )
    )
}
