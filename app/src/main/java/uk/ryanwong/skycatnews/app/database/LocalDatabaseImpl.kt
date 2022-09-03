/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.local.NewsListDao
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.local.model.NewsItemEntity
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.local.model.NewsListEntity
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.local.StoryDao
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.local.model.ContentEntity
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.local.model.StoryEntity

@Database(
    entities = [
        NewsItemEntity::class,
        NewsListEntity::class,
        ContentEntity::class,
        StoryEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class LocalDatabaseImpl : LocalDatabase, RoomDatabase() {
    abstract override fun NewsListDao(): NewsListDao
    abstract override fun StoryDao(): StoryDao
}
