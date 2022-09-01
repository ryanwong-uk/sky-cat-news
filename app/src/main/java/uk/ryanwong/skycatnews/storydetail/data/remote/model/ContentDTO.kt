/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ContentDTO(
    val accessibilityText: String? = null,
    val text: String? = null,
    val type: String? = null,
    val url: String? = null,
)
