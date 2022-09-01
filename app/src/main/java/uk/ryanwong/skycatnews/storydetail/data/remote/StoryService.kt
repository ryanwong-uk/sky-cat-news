/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.remote

import uk.ryanwong.skycatnews.storydetail.data.remote.model.StoryDTO

interface StoryService {

    suspend fun getStory(storyId: Int): StoryDTO

    companion object {
        const val BASE_URL = "http://192.168.1.1/"
    }

    sealed class Endpoints(val url: String) {
        object GetAllItems : Endpoints("$BASE_URL/story")
    }
}