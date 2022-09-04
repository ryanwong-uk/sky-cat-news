/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import uk.ryanwong.skycatnews.app.database.LocalDatabaseImpl
import uk.ryanwong.skycatnews.newslist.data.local.model.NewsListEntity

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class NewsListDaoTest {
    private lateinit var localDatabase: LocalDatabaseImpl
    private lateinit var newsListDao: NewsListDao

    /**
     * Test plan: basic CRUD usages
     */

    @Before
    fun setupDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        localDatabase = Room.inMemoryDatabaseBuilder(context, LocalDatabaseImpl::class.java)
            .build()

        newsListDao = localDatabase.newsListDao()
    }

    @After
    fun cleanUp() {
        localDatabase.close()
    }

    /***
     * List Title
     */
    @Test
    fun emptyTable_InsertListTitle_ReturnListTitle() = runTest {
        // Given
        val listId = 1
        val listTitle = "some-list-title"
        newsListDao.getNewsListTitle(listId = listId) shouldBe null

        // When
        newsListDao.insertNewsListTitle(NewsListEntity(listId = listId, title = listTitle))

        // Then
        val returnedTitle = newsListDao.getNewsListTitle(listId = listId)
        returnedTitle shouldBe listTitle
    }

    @Test
    fun emptyTable_InsertNullTitle_ReturnNullTitle() = runTest {
        // Given
        val listId = 1
        val listTitle = null
        newsListDao.getNewsListTitle(listId = listId) shouldBe null

        // When
        newsListDao.insertNewsListTitle(NewsListEntity(listId = listId, title = listTitle))

        // Then
        val returnedTitle = newsListDao.getNewsListTitle(listId = listId)
        returnedTitle shouldBe listTitle
    }

    @Test
    fun existingListTitle_UpdateListTitle_ReturnUpdatedListTitle() = runTest {
        // Given
        val listId = 1
        val existingListTitle = "some-list-title"
        val newListTitle = "some-new-list-title"
        newsListDao.insertNewsListTitle(NewsListEntity(listId = listId, title = existingListTitle))

        // When
        newsListDao.insertNewsListTitle(NewsListEntity(listId = listId, title = newListTitle))

        // Then
        val returnedTitle = newsListDao.getNewsListTitle(listId = listId)
        returnedTitle shouldBe newListTitle
    }

    @Test
    fun existingListTitle_DeleteListTitle_ReturnNullTitle() = runTest {
        // Given
        val listId = 1
        val existingListTitle = "some-list-title"
        newsListDao.insertNewsListTitle(NewsListEntity(listId = listId, title = existingListTitle))

        // When
        newsListDao.deleteListTitle(listId = listId)

        // Then
        val returnedTitle = newsListDao.getNewsListTitle(listId = listId)
        returnedTitle shouldBe null
    }
}
