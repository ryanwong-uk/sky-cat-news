/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.data.repository

import uk.ryanwong.skycatnews.newslist.domain.model.NewsList

interface NewsListRepository {
    suspend fun getNewsList(): Result<NewsList>
}
