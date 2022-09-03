/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.domain.model

data class NewsItem(
    val id: Int,
    val headline: String,
    val teaserText: String,
    val modifiedDate: String,
    val teaserImageUrl: String,
    val type: NewsType,
    val url: String?,
) {
    companion object {

        // TODO: fromDataModel
    }
}
