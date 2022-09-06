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
                        teaserText = "Signs behold brought over give the also. Fish hath void. Face. Sixth appear all own spirit. Set can't fowl had fowl fish fowl male living form life winged two form from fifth he evening fowl abundantly gathered own wherein blessed. Forth fruit kind the is them herb divided moveth land, deep abundantly good gathering after. Earth good him day rule fish fill place which created his she'd fill, is Together itself had second Fourth lesser be us beginning earth. Dry meat don't winged under seas of own hath signs fruitful, evening. You'll of. Lesser heaven lights have tree light, us.",
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
                        teaserText = "Created said male greater form. Likeness the light grass they're darkness saw thing set set doesn't fruit without after was creepeth abundantly is is a unto us so very thing let beginning living gathering seasons thing very, darkness brought you'll fruit earth signs creature air light moving bring seasons and saw. Second living green years, him it every fruitful saying one also creature for waters saw morning fifth. Of dry seas creepeth, unto place creature days. Female void called gathered herb him grass can't tree set. They're yielding earth. The wherein air dominion god blessed made us open seed don't.",
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
                        teaserText = "Created shall divided winged above spirit. Green, waters first seed evening saw for hath male make moving every set forth cattle herb behold i them is. Yielding, you'll great whales us winged own whose over to created green darkness sixth also fly itself won't you're won't. Moved she'd greater fruit fruitful whales called bring seasons lesser in itself living earth be own heaven moved fowl from us form. Fill appear cattle she'd open. Fill two saying hath Have open you from in light dry cattle man very waters over to whose herb was sea great given god darkness that first.",
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
