/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LinksDTO(
    val url: UrlDTO? = null,
)
