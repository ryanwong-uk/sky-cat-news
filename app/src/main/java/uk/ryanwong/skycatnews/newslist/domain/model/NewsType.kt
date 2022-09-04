/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.domain.model

enum class NewsType {
    UNKNOWN,
    STORY,
    ADVERT,
    WEBLINK,
}

fun NewsType.parse(newsType: String?): NewsType {
    return newsType?.let {
        try {
            NewsType.valueOf(it)
        } catch (e: IllegalArgumentException) {
            NewsType.UNKNOWN
        }
    } ?: NewsType.UNKNOWN
}
