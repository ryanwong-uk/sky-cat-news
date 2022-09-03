/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.remote

import uk.ryanwong.skycatnews.BuildConfig
import uk.ryanwong.skycatnews.newslist.data.remote.model.NewsListDTO

interface NewsListService {

    suspend fun getAllItems(): Result<NewsListDTO>

    companion object {
        const val BASE_URL = BuildConfig.DEFAULT_BASE_URL
    }

    sealed class Endpoints(val url: String) {
        object GetAllItems : Endpoints("$BASE_URL/news-list")
    }
}
