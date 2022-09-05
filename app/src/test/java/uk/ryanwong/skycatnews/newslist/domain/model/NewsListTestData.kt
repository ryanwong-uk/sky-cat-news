/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.domain.model

import uk.ryanwong.skycatnews.newslist.data.local.entity.NewsItemEntity

internal object NewsListTestData {

    val mockNewsItemEntity1 by lazy {
        NewsItemEntity(
            listId = 1,
            newsId = 1,
            type = "story",
            headline = "some-headline",
            creationDate = "2022-5-21T00:00:00Z",
            modifiedDate = "2022-5-21T00:00:00Z",
            url = "https://some.url/",
            weblinkUrl = "https://some.weblink.url/",
            teaserText = "some-teaser-text",
            teaserImageHref = "https://some.teaser.image/href",
            teaserImageTemplated = true,
            teaserImageType = "some-teaser-image-type",
            teaserImageAccessibilityText = "some-teaser-image-accessibility-text"
        )
    }

    val mockNewsItemEntity2 by lazy {
        NewsItemEntity(
            listId = 1,
            newsId = 2,
            type = "weblink",
            headline = "some-headline-2",
            creationDate = "2022-5-21T00:00:01Z",
            modifiedDate = "2022-5-21T00:00:01Z",
            url = "https://some.url/2",
            weblinkUrl = "https://some.weblink.url/2",
            teaserText = "some-teaser-text-2",
            teaserImageHref = "https://some.teaser.image/href/2",
            teaserImageTemplated = true,
            teaserImageType = "some-teaser-image-type-2",
            teaserImageAccessibilityText = "some-teaser-image-accessibility-text-2"
        )
    }

    val mockNewsItemEntity3 by lazy {
        NewsItemEntity(
            listId = 1,
            newsId = 3,
            type = "some-type-3",
            headline = "some-headline-3",
            creationDate = "2022-5-21T00:00:02Z",
            modifiedDate = "2022-5-21T00:00:02Z",
            url = "https://some.url/3",
            weblinkUrl = "https://some.weblink.url/3",
            teaserText = "some-teaser-text-3",
            teaserImageHref = "https://some.teaser.image/href/3",
            teaserImageTemplated = true,
            teaserImageType = "some-teaser-image-type-3",
            teaserImageAccessibilityText = "some-teaser-image-accessibility-text-3"
        )
    }

    val mockNewsItem1 by lazy {
        NewsItem(
            newsId = 1,
            headline = "some-headline",
            teaserText = "some-teaser-text",
            modifiedDate = "2022-5-21T00:00:00Z",
            teaserImageUrl = "https://some.teaser.image/href",
            type = NewsType.STORY,
            url = "https://some.url/"
        )
    }

    val mockNewsItem2 by lazy {
        NewsItem(
            newsId = 2,
            headline = "some-headline-2",
            teaserText = "some-teaser-text-2",
            modifiedDate = "2022-5-21T00:00:01Z",
            teaserImageUrl = "https://some.teaser.image/href/2",
            type = NewsType.WEBLINK,
            url = "https://some.url/2"
        )
    }

    val mockNewsItem3 by lazy {
        NewsItem(
            newsId = 3,
            headline = "some-headline-3",
            teaserText = "some-teaser-text-3",
            modifiedDate = "2022-5-21T00:00:02Z",
            teaserImageUrl = "https://some.teaser.image/href/3",
            type = NewsType.UNKNOWN,
            url = "https://some.url/3"
        )
    }
}
