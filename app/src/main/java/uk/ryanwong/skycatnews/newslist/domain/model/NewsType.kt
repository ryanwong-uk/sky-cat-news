/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.domain.model

enum class NewsType {
    UNKNOWN,
    STORY,
    ADVERT,
    WEBLINK;

    companion object {
        fun parse(newsType: String?): NewsType {
            return newsType?.let {
                try {
                    valueOf(it.uppercase())
                } catch (e: IllegalArgumentException) {
                    UNKNOWN
                }
            } ?: UNKNOWN
        }
    }
}
