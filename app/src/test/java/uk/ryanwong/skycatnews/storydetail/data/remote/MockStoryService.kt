/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.data.remote

import uk.ryanwong.skycatnews.storydetail.data.remote.model.StoryDto

internal class MockStoryService : StoryService {
    var mockGetStoryResponseException: Exception? = null
    var mockGetStoreResponse: Result<StoryDto?>? = null

    override suspend fun getStory(storyId: Int): Result<StoryDto?> {
        mockGetStoryResponseException?.let { throw it }
        return mockGetStoreResponse ?: throw Exception("mock response not defined")
    }
}
