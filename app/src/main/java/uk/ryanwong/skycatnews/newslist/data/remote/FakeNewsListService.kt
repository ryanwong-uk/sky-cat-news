/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.remote

import uk.ryanwong.skycatnews.newslist.data.remote.model.LinksDto
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsItemDto
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsListDto
import uk.ryanwong.skycatnews.newslist.data.remote.model.TeaserImageDto
import uk.ryanwong.skycatnews.newslist.data.remote.model.UrlDto

class FakeNewsListService : NewsListService {
    override suspend fun getAllItems(): Result<NewsListDto> {
        return Result.success(
            NewsListDto(
                news = listOf(
                    NewsItemDto(
                        creationDate = "2020-11-18T00:00:00Z",
                        headline = "Story Headline",
                        id = 1,
                        modifiedDate = "2020-11-19T00:00:00Z",
                        teaserImage = TeaserImageDto(
                            links = LinksDto(
                                url = UrlDto(
                                    href = "https://ryanwong.co.uk/sample-resources/skycatnews/cat1_hero.webp",
                                    templated = true,
                                    type = "image/webp"
                                )
                            ),
                            accessibilityText = "Image content description"
                        ),
                        teaserText = "Story teaser text",
                        type = "story",
                        advertUrl = null,
                        weblinkUrl = null
                    ),
                    NewsItemDto(
                        creationDate = "2020-11-18T00:00:00Z",
                        headline = "Story Headline",
                        id = 2,
                        modifiedDate = "2020-11-19T00:00:00Z",
                        teaserImage = TeaserImageDto(
                            links = LinksDto(
                                url = UrlDto(
                                    href = "https://ryanwong.co.uk/sample-resources/skycatnews/cat2_hero.webp",
                                    templated = true,
                                    type = "image/webp"
                                )
                            ),
                            accessibilityText = "Image content description"
                        ),
                        teaserText = "Story teaser text",
                        type = "story",
                        advertUrl = null,
                        weblinkUrl = null
                    ),
                    NewsItemDto(
                        creationDate = null,
                        headline = null,
                        id = null,
                        modifiedDate = null,
                        teaserImage = null,
                        teaserText = null,
                        type = "advert",
                        advertUrl = "advert/url",
                        weblinkUrl = null
                    ),
                    NewsItemDto(
                        creationDate = "2020-11-18T00:00:00Z",
                        headline = "Weblink headline",
                        id = 3,
                        modifiedDate = "2020-11-19T00:00:00Z",
                        teaserImage = TeaserImageDto(
                            links = LinksDto(
                                url = UrlDto(
                                    href = "https://ryanwong.co.uk/sample-resources/skycatnews/cat3_hero.webp",
                                    templated = true,
                                    type = "image/webp"
                                )
                            ),
                            accessibilityText = "Image content description"
                        ),
                        teaserText = null,
                        type = "weblink",
                        advertUrl = null,
                        weblinkUrl = "https://news.sky.com/story/john-shuttleworth-comedy-gig-inside-cave-halted-halfway-after-fan-gets-trapped-in-tree-above-gorge-12617846"
                    ),
                    NewsItemDto(
                        creationDate = "2020-11-18T00:00:00Z",
                        headline = "Story headline",
                        id = 4,
                        modifiedDate = "2020-11-19T00:00:00Z",
                        teaserImage = TeaserImageDto(
                            links = LinksDto(
                                url = UrlDto(
                                    href = "https://ryanwong.co.uk/sample-resources/skycatnews/cat4_hero.webp",
                                    templated = true,
                                    type = "image/webp"
                                )
                            ),
                            accessibilityText = "Image content description"
                        ),
                        teaserText = "Story teaser text",
                        type = "story",
                        advertUrl = null,
                        weblinkUrl = null
                    ),
                    NewsItemDto(
                        creationDate = "2020-11-18T00:00:00Z",
                        headline = "Weblink headline",
                        id = 5,
                        modifiedDate = "2020-11-19T00:00:00Z",
                        teaserImage = TeaserImageDto(
                            links = LinksDto(
                                url = UrlDto(
                                    href = "https://ryanwong.co.uk/sample-resources/skycatnews/cat5_hero.webp",
                                    templated = true,
                                    type = "image/webp"
                                )
                            ),
                            accessibilityText = "Image content description"
                        ),
                        teaserText = null,
                        type = "weblink",
                        advertUrl = null,
                        weblinkUrl = "https://news.sky.com/story/tory-leadership-rishi-sunak-and-liz-truss-promise-to-increase-scrutiny-of-scottish-govt-as-they-head-to-perth-12674081"
                    )
                ),
                title = "Sky Cat News"
            )
        )
    }
}
