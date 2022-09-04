/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class StoryDto(
    val id: Int? = null,
    val contents: List<ContentDto>? = null,
    val creationDate: String? = null,
    val headline: String? = null,
    val heroImage: HeroImageDto? = null,
    val modifiedDate: String? = null,
)
