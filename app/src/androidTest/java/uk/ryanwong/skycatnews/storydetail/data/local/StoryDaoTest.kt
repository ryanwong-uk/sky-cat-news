/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.data.local

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
import uk.ryanwong.skycatnews.storydetail.data.local.model.StoryEntity

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
internal class StoryDaoTest {
    private lateinit var localDatabase: LocalDatabaseImpl
    private lateinit var storyDao: StoryDao

    /**
     * Test plan: basic CRUD usages with some special cases
     */
    @Before
    fun setupDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        localDatabase = Room.inMemoryDatabaseBuilder(context, LocalDatabaseImpl::class.java)
            .build()

        storyDao = localDatabase.storyDao()
    }

    @After
    fun cleanUp() {
        localDatabase.close()
    }

    /**
     * Story
     */
    @Test
    fun emptyTable_InsertStory_ReturnStory() = runTest {
        // Given
        val storyId = 1
        val story = StoryEntity(
            storyId = storyId,
            headline = "some-headline",
            heroImageUrl = "https://some.hero.image/url",
            heroImageAccessibilityText = "some-accessibility-text",
            creationDate = "2022-5-21T00:00:00Z",
            modifiedDate = "2022-5-21T00:00:00Z"
        )
        storyDao.getStory(storyId = storyId) shouldBe null

        // When
        storyDao.insertStory(story = story)

        // Then
        val returnedStory = storyDao.getStory(storyId = storyId)
        returnedStory shouldBe story
    }

    @Test
    fun existingStory_UpdateStory_ReturnUpdatedStory() = runTest {
        // Given
        val storyId = 1
        val existingStory = StoryEntity(
            storyId = storyId,
            headline = "some-headline",
            heroImageUrl = "https://some.hero.image/url",
            heroImageAccessibilityText = "some-accessibility-text",
            creationDate = "2022-5-21T00:00:00Z",
            modifiedDate = "2022-5-21T00:00:00Z"
        )
        val newStory = StoryEntity(
            storyId = storyId,
            headline = "some-new-headline",
            heroImageUrl = "https://some.hero.image/new-url",
            heroImageAccessibilityText = "some-new-accessibility-text",
            creationDate = "2022-5-21T00:00:01Z",
            modifiedDate = "2022-5-21T00:00:01Z"
        )
        storyDao.insertStory(story = existingStory)

        // When
        storyDao.insertStory(story = newStory)

        // Then
        val returnedStory = storyDao.getStory(storyId = storyId)
        returnedStory shouldBe newStory
    }

    @Test
    fun existingStory_DeleteStory_ReturnNullTitle() = runTest {
        // Given
        val storyId = 1
        val story = StoryEntity(
            storyId = storyId,
            headline = "some-headline",
            heroImageUrl = "https://some.hero.image/url",
            heroImageAccessibilityText = "some-accessibility-text",
            creationDate = "2022-5-21T00:00:00Z",
            modifiedDate = "2022-5-21T00:00:00Z"
        )
        storyDao.insertStory(story = story)

        // When
        storyDao.deleteStory(storyId = storyId)

        // Then
        val returnedStory = storyDao.getStory(storyId = storyId)
        returnedStory shouldBe null
    }
}
