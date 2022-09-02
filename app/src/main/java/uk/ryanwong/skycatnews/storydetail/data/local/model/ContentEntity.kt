/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contents")
data class ContentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sequence_id")
    val sequenceId: Int,
    @ColumnInfo(name = "story_id")
    val storyId: Int,
    @ColumnInfo(name = "accessibility_text")
    val accessibilityText: String,
    val text: String,
    val type: String,
    val url: String,
)
