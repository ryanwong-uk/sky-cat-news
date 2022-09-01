package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.remote

interface StoryService {

    suspend fun getStory(storyId: Int): String

    companion object {
        const val BASE_URL = "http://192.168.1.1/"
    }

    sealed class Endpoints(val url: String) {
        object GetAllItems : Endpoints("$BASE_URL/story")
    }
}
