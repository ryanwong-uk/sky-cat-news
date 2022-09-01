/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class StoryDTO(
    val id: Int? = null,
    val contents: List<ContentDTO>? = null,
    val creationDate: String? = null,
    val headline: String? = null,
    val heroImage: HeroImageDTO? = null,
    val modifiedDate: String? = null,
)
