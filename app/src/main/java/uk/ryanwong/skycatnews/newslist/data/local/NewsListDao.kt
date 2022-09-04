/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.ryanwong.skycatnews.newslist.data.local.model.NewsItemEntity
import uk.ryanwong.skycatnews.newslist.data.local.model.NewsListEntity

@Dao
interface NewsListDao {
    @Query("SELECT title FROM news_list WHERE list_id = :listId LIMIT 1")
    suspend fun getNewsListTitle(listId: Int): String

    @Query("SELECT * FROM news_items WHERE list_id = :listId ORDER BY modified_date DESC")
    suspend fun getNewsList(listId: Int): List<NewsItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsItems(newsItems: List<NewsItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsListTitle(newsListEntity: NewsListEntity)

    @Query("DELETE FROM news_list WHERE list_id = :listId ")
    suspend fun deleteListTitle(listId: Int)

    @Query("DELETE FROM news_items WHERE list_id = :listId ")
    suspend fun deleteNewsItems(listId: Int)
}
