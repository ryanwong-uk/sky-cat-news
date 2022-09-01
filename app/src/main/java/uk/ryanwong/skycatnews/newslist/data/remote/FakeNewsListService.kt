/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.remote

import uk.ryanwong.skycatnews.newslist.data.remote.model.LinksDTO
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsItemDTO
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsListDTO
import uk.ryanwong.skycatnews.newslist.data.remote.model.TeaserImageDTO
import uk.ryanwong.skycatnews.newslist.data.remote.model.UrlDTO

class FakeNewsListService : NewsListService {
    override suspend fun getAllItems(): Result<NewsListDTO> {
        return Result.success(
            NewsListDTO(
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
        )
    }
}
