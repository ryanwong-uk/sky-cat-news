/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.domain.model

data class Content(
    val accessibilityText: String?,
    val text: String,
    val type: StoryContentType,
    val url: String?,
)
