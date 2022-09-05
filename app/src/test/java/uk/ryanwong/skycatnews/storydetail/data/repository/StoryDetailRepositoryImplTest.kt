/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.Storydetail.data.repository

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import uk.ryanwong.skycatnews.storydetail.data.local.MockStoryDao
import uk.ryanwong.skycatnews.storydetail.data.local.entity.StoryEntity
import uk.ryanwong.skycatnews.storydetail.data.remote.MockStoryService
import uk.ryanwong.skycatnews.storydetail.data.repository.StoryDetailRepository
import uk.ryanwong.skycatnews.storydetail.data.repository.StoryDetailRepositoryImpl
import uk.ryanwong.skycatnews.storydetail.data.repository.StoryDetailRepositoryImplTestData

@OptIn(ExperimentalCoroutinesApi::class)
internal class StoryDetailRepositoryImplTest : FreeSpec() {

    private lateinit var scope: TestScope
    private lateinit var storyDetailRepository: StoryDetailRepository
    private lateinit var mockStoryService: MockStoryService
    private lateinit var mockStoryDao: MockStoryDao

    private fun setupRepository() {
        val dispatcher = StandardTestDispatcher()
        scope = TestScope(dispatcher)

        mockStoryService = MockStoryService()
        mockStoryDao = MockStoryDao()
        storyDetailRepository = StoryDetailRepositoryImpl(
            storyService = mockStoryService,
            storyDao = mockStoryDao,
            dispatcher = dispatcher
        )
    }

    init {
        "getStory" - {
            "Remote data source returned success" - {
                "Should update local database" {
                    setupRepository()
                    scope.runTest {
                        // Given
                        val storyDto = StoryDetailRepositoryImplTestData.mockStoryDto
                        mockStoryService.mockGetStoreResponse = Result.success(storyDto)
                        val storyId = StoryDetailRepositoryImplTestData.mockStoryDto.id

                        // When
                        storyDetailRepository.getStory(storyId = storyId)

                        // Then
                        mockStoryDao.mockInsertStoryReceivedValue shouldBe StoryEntity(
                            storyId = storyId,
                            headline = "some-head-line",
                            heroImageUrl = "https://some.hero.image/url",
                            heroImageAccessibilityText = "some-accessibility-text",
                            creationDate = "2020-11-18T00:00:00Z",
                            modifiedDate = "2020-11-19T00:00:00Z"
                        )
                        mockStoryDao.mockDeleteContentsReceivedValue shouldBe storyId
                        mockStoryDao.mockInsertContentsReceivedValue shouldBe listOf(
                            StoryDetailRepositoryImplTestData.getMockContentEntity(storyId = storyId)
                        )
                    }
                }

                "Should return Result.Success<Story>" {
                    setupRepository()
                    scope.runTest {
                        // Given

                        // Only to trigger a success response,
                        // actual data to be tested is from mockStoryDao.mockGetStoryResponse
                        val storyDto = StoryDetailRepositoryImplTestData.mockStoryDto
                        mockStoryService.mockGetStoreResponse = Result.success(storyDto)

                        val storyId = StoryDetailRepositoryImplTestData.mockStoryDto.id
                        mockStoryDao.mockGetStoryResponse =
                            StoryDetailRepositoryImplTestData.getMockStoryEntity(storyId = storyId)
                        mockStoryDao.mockGetContentsResponse = listOf(
                            StoryDetailRepositoryImplTestData.getMockContentEntity(storyId = storyId)
                        )

                        // When
                        val story = storyDetailRepository.getStory(storyId = storyId)

                        // Then
                        story shouldBe Result.success(StoryDetailRepositoryImplTestData.mockStoryId1)
                    }
                }
            }
        }
    }
}
