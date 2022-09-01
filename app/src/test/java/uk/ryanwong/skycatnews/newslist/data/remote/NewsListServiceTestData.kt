/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.remote

import uk.ryanwong.skycatnews.newslist.data.remote.model.LinksDTO
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsItemDTO
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsListDTO
import uk.ryanwong.skycatnews.newslist.data.remote.model.TeaserImageDTO
import uk.ryanwong.skycatnews.newslist.data.remote.model.UrlDTO

internal object NewsListServiceTestData {

    val mockJsonResponse = """{
    "title": "Sky Cat News",
    "data": [
    {
        "id": "1",
        "type": "story",
        "headline": "Story Headline",
        "teaserText": "Story teaser text",
        "creationDate": "2020-11-18T00:00:00Z",
        "modifiedDate": "2020-11-19T00:00:00Z",
        "teaserImage": {
        "_links": {
        "url": {
        "href": "",
        "templated": true,
        "type": "image/jpeg"
    }
    },
        "accessibilityText": "Image content description"
    }
    },
    {
        "id": "2",
        "type": "story",
        "headline": "Story Headline",
        "teaserText": "Story teaser text",
        "creationDate": "2020-11-18T00:00:00Z",
        "modifiedDate": "2020-11-19T00:00:00Z",
        "teaserImage": {
        "_links": {
        "url": {
        "href": "",
        "templated": true,
        "type": "image/jpeg"
    }
    },
        "accessibilityText": "Image content description"
    }
    },
    {
        "type": "advert",
        "url": "advert/url"
    },
    {
        "id": "3",
        "type": "weblink",
        "headline": "Weblink headline",
        "weblinkUrl": "weblink url",
        "creationDate": "2020-11-18T00:00:00Z",
        "modifiedDate": "2020-11-19T00:00:00Z",
        "teaserImage": {
        "_links": {
        "url": {
        "href": "",
        "templated": true,
        "type": "image/jpeg"
    }
    },
        "accessibilityText": "Image content description"
    }
    },
    {
        "id": "4",
        "type": "story",
        "headline": "Story headline",
        "teaserText": "Story teaser text",
        "creationDate": "2020-11-18T00:00:00Z",
        "modifiedDate": "2020-11-19T00:00:00Z",
        "teaserImage": {
        "_links": {
        "url": {
        "href": "",
        "templated": true,
        "type": "image/jpeg"
    }
    },
        "accessibilityText": "Image content description"
    }
    },
    {
        "id": "5",
        "type": "weblink",
        "headline": "Weblink headline",
        "weblinkUrl": "",
        "creationDate": "2020-11-18T00:00:00Z",
        "modifiedDate": "2020-11-19T00:00:00Z",
        "teaserImage": {
        "_links": {
        "url": {
        "href": "",
        "templated": true,
        "type": "image/jpeg"
    }
    },
        "accessibilityText": "Image content description"
    }
    }
    ]
}
"""

    val mockNewsListDTO = NewsListDTO(
        news = listOf(
            NewsItemDTO(
                creationDate = "2020-11-18T00:00:00Z",
                headline = "Story Headline",
                id = 1,
                modifiedDate = "2020-11-19T00:00:00Z",
                teaserImage = TeaserImageDTO(
                    links = LinksDTO(
                        url = UrlDTO(
                            href = "",
                            templated = true,
                            type = "image/jpeg"
                        )
                    ),
                    accessibilityText = "Image content description"
                ),
                teaserText = "Story teaser text",
                type = "story",
                url = null,
                weblinkUrl = null
            ),
            NewsItemDTO(
                creationDate = "2020-11-18T00:00:00Z",
                headline = "Story Headline",
                id = 2,
                modifiedDate = "2020-11-19T00:00:00Z",
                teaserImage = TeaserImageDTO(
                    links = LinksDTO(
                        url = UrlDTO(
                            href = "",
                            templated = true,
                            type = "image/jpeg"
                        )
                    ),
                    accessibilityText = "Image content description"
                ),
                teaserText = "Story teaser text",
                type = "story",
                url = null,
                weblinkUrl = null
            ),
            NewsItemDTO(
                creationDate = null,
                headline = null,
                id = null,
                modifiedDate = null,
                teaserImage = null,
                teaserText = null,
                type = "advert",
                url = "advert/url",
                weblinkUrl = null
            ),
            NewsItemDTO(
                creationDate = "2020-11-18T00:00:00Z",
                headline = "Weblink headline",
                id = 3,
                modifiedDate = "2020-11-19T00:00:00Z",
                teaserImage = TeaserImageDTO(
                    links = LinksDTO(
                        url = UrlDTO(
                            href = "",
                            templated = true,
                            type = "image/jpeg"
                        )
                    ),
                    accessibilityText = "Image content description"
                ),
                teaserText = null,
                type = "weblink",
                url = null,
                weblinkUrl = "weblink url"
            ),
            NewsItemDTO(
                creationDate = "2020-11-18T00:00:00Z",
                headline = "Story headline",
                id = 4,
                modifiedDate = "2020-11-19T00:00:00Z",
                teaserImage = TeaserImageDTO(
                    links = LinksDTO(
                        url = UrlDTO(
                            href = "",
                            templated = true,
                            type = "image/jpeg"
                        )
                    ),
                    accessibilityText = "Image content description"
                ),
                teaserText = "Story teaser text",
                type = "story",
                url = null,
                weblinkUrl = null
            ),
            NewsItemDTO(
                creationDate = "2020-11-18T00:00:00Z",
                headline = "Weblink headline",
                id = 5,
                modifiedDate = "2020-11-19T00:00:00Z",
                teaserImage = TeaserImageDTO(
                    links = LinksDTO(
                        url = UrlDTO(
                            href = "",
                            templated = true,
                            type = "image/jpeg"
                        )
                    ),
                    accessibilityText = "Image content description"
                ),
                teaserText = null,
                type = "weblink",
                url = null,
                weblinkUrl = ""
            )
        ),
        title = "Sky Cat News"
    )
}
