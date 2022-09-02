/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero_images")
data class HeroImageEntity(
    @PrimaryKey
    @ColumnInfo(name = "story_id")
    val storyId: Int,
    @ColumnInfo(name = "accessibility_text")
    val accessibilityText: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
)
