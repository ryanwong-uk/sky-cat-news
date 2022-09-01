/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsListDTO(
    @SerialName(value = "data")
    val news: List<NewsItemDTO>? = null,
    val title: String? = null,
)
