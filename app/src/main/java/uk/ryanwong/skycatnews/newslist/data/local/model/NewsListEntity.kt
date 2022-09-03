/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.local.model

import androidx.room.Entity

@Entity(tableName = "news_list")
data class NewsListEntity(
    val title: String,
)
