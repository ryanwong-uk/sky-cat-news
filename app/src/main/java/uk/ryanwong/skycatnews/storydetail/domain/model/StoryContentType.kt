/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.domain.model

enum class StoryContentType {
    UNKNOWN,
    IMAGE,
    PARAGRAPH;

    companion object {
        fun parse(storyContentType: String?): StoryContentType {
            return storyContentType?.let {
                try {
                    valueOf(it.uppercase())
                } catch (e: IllegalArgumentException) {
                    UNKNOWN
                }
            } ?: UNKNOWN
        }
    }
}
