/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.app.database

import uk.ryanwong.skycatnews.newslist.data.local.NewsListDao
import uk.ryanwong.skycatnews.storydetail.data.local.StoryDao

interface LocalDatabase {
    fun newsListDao(): NewsListDao
    fun storyDao(): StoryDao
}
