package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CancellationException

class NewsListServiceImpl(
    private val httpClient: HttpClient
) : NewsListService {

    // TODO: return DTO
    // TODO: wrap with Result()
    override suspend fun getAllItems(): List<String> {
        return try {
            httpClient.get(NewsListService.Endpoints.GetAllItems.url).body()
        } catch (cancellationException: CancellationException) {
            throw cancellationException
        } catch (ex: Exception) {
            emptyList()
        }
    }
}
