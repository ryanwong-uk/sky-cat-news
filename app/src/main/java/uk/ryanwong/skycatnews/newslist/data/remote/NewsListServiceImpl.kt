/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsListDTO

class NewsListServiceImpl(
    private val httpClient: HttpClient
) : NewsListService {

    override suspend fun getAllItems(): Result<NewsListDTO> {
        return httpClient.get(NewsListService.Endpoints.GetAllItems.url).body()
    }
}
