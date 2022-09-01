package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.CancellationException

class StoryServiceImpl(
    private val httpClient: HttpClient
) : StoryService {

    // TODO: return DTO
    // TODO: wrap with Result()
    override suspend fun getStory(storyId: Int): String {
        return try {
            httpClient.get(StoryService.Endpoints.GetAllItems.url) {
                url {
                    appendPathSegments(storyId.toString())
                }
            }.body()
        } catch (cancellationException: CancellationException) {
            throw cancellationException
        } catch (ex: Exception) {
            ""
        }
    }
}
