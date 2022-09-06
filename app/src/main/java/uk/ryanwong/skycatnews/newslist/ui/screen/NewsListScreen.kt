/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import uk.ryanwong.skycatnews.app.ui.theme.SkyCatNewsTheme
import uk.ryanwong.skycatnews.newslist.domain.model.NewsItem
import uk.ryanwong.skycatnews.newslist.ui.screen.component.LargeStoryHeadline
import uk.ryanwong.skycatnews.newslist.ui.screen.component.LargeWebLinkHeadline
import uk.ryanwong.skycatnews.newslist.ui.screen.component.RegularStoryHeadline
import uk.ryanwong.skycatnews.newslist.ui.screen.component.RegularWebLinkHeadline
import uk.ryanwong.skycatnews.newslist.ui.screen.component.SkyCatNewsAppBar
import uk.ryanwong.skycatnews.newslist.ui.viewmodel.NewsListViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun NewsListScreen(
    newsListViewModel: NewsListViewModel = hiltViewModel(),
    onStoryItemClicked: (id: Int) -> Unit,
    onWebLinkItemClicked: (id: Int) -> Unit,
) {
    val isRefreshing by newsListViewModel.isRefreshing.collectAsStateWithLifecycle()
    val newsList by newsListViewModel.newsList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        SkyCatNewsAppBar()

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = { newsListViewModel.refreshNewsList() }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                if (newsList.isNotEmpty()) {
                    val mutableNewsList = newsList.toMutableList()

                    item {
                        val firstItem = mutableNewsList.removeFirst()
                        when (firstItem) {
                            is NewsItem.Story -> {
                                LargeStoryHeadline(
                                    story = firstItem,
                                    onItemClicked = { onStoryItemClicked(firstItem.newsId) },
                                )
                            }
                            is NewsItem.WebLink -> {
                                LargeWebLinkHeadline(
                                    webLink = firstItem,
                                    onItemClicked = { onWebLinkItemClicked(firstItem.newsId) },
                                )
                            }
                        }
                    }

                    itemsIndexed(mutableNewsList) { _, newsItem ->
                        when (newsItem) {
                            is NewsItem.Story -> {
                                RegularStoryHeadline(
                                    story = newsItem,
                                    onItemClicked = { onStoryItemClicked(newsItem.newsId) },
                                )
                            }
                            is NewsItem.WebLink -> {
                                RegularWebLinkHeadline(
                                    webLink = newsItem,
                                    onItemClicked = { onWebLinkItemClicked(newsItem.newsId) },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun NewsListScreenPreview() {
    SkyCatNewsTheme {
        NewsListScreen(
            onStoryItemClicked = {},
            onWebLinkItemClicked = {},
        )
    }
}
