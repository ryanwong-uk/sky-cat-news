/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.domain.model

import uk.ryanwong.skycatnews.newslist.data.local.entity.NewsItemEntity

data class NewsList(
    val title: String,
    val newsItems: List<NewsItem>,
) {
    companion object {
        fun fromEntity(
            title: String?,
            newsItemEntities: List<NewsItemEntity>,
        ): NewsList {
            return NewsList(
                title = title ?: "",
                newsItems = NewsItem.fromEntity(newsItemEntities = newsItemEntities)
            )
        }
    }

    fun isEmpty(): Boolean {
        return newsItems.isEmpty()
    }
}
