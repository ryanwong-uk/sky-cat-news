/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsItemDto(
    val id: Int? = null,
    val creationDate: String? = null,
    val headline: String? = null,
    val modifiedDate: String? = null,
    val teaserImage: TeaserImageDto? = null,
    val teaserText: String? = null,
    val type: String? = null,
    val url: String? = null,
    val weblinkUrl: String? = null,
)
