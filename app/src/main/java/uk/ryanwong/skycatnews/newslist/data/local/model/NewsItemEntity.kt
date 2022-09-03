/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_items")
data class NewsItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "news_id")
    val newsId: Int,
    val type: String,
    val headline: String,
    @ColumnInfo(name = "creation_date")
    val creationDate: String,
    @ColumnInfo(name = "modified_date")
    val modifiedDate: String,
    val url: String,
    @ColumnInfo(name = "weblink_url")
    val weblinkUrl: String,

    @ColumnInfo(name = "teaser_text")
    val teaserText: String,
    @ColumnInfo(name = "teaser_image_href")
    val teaserImageHref: String,
    @ColumnInfo(name = "teaser_image_templated")
    val teaserImageTemplated: Boolean,
    @ColumnInfo(name = "teaser_image_type")
    val teaserImageType: String,
    @ColumnInfo(name = "teaser_image_accessibility_text")
    val teaserImageAccessibilityText: String,
)
