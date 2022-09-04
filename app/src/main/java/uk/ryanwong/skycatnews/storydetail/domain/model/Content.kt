/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.domain.model

import uk.ryanwong.skycatnews.storydetail.data.local.model.ContentEntity

data class Content(
    val accessibilityText: String?,
    val text: String,
    val type: StoryContentType,
    val url: String?,
) {
    companion object {
        fun fromEntity(
            contentEntities: List<ContentEntity>,
        ): List<Content> {
            return contentEntities.map { contentEntity ->
                Content(
                    accessibilityText = contentEntity.accessibilityText,
                    text = contentEntity.text,
                    type = StoryContentType.parse(contentEntity.type),
                    url = contentEntity.url
                )
            }
        }
    }
}
