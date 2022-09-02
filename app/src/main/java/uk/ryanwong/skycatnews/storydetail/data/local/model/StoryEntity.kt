/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stories")
data class StoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "story_id")
    val storyId: Int,
    @ColumnInfo(name = "creation_date")
    val creationDate: String,
    val headline: String,
    @ColumnInfo(name = "modified_date")
    val modifiedDate: String,
)
