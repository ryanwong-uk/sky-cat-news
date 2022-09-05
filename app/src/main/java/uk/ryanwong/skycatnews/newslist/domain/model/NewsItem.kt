/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.domain.model

import uk.ryanwong.skycatnews.newslist.data.local.entity.NewsItemEntity

data class NewsItem(
    val newsId: Int,
    val headline: String,
    val teaserText: String,
    val modifiedDate: String,
    val teaserImageUrl: String,
    val type: NewsType,
    val url: String?,
) {
    companion object {

        fun fromEntity(newsItemEntities: List<NewsItemEntity>): List<NewsItem> {
            return newsItemEntities.map { newsItemEntity ->
                with(newsItemEntity) {
                    NewsItem(
                        newsId = newsId,
                        headline = headline ?: "",
                        teaserText = teaserText ?: "",
                        modifiedDate = modifiedDate,
                        teaserImageUrl = teaserImageHref ?: "",
                        type = NewsType.parse(type ?: ""),
                        url = url
                    )
                }
            }
        }
    }
}
