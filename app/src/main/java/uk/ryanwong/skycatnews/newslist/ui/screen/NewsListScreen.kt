/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import uk.ryanwong.skycatnews.R
import uk.ryanwong.skycatnews.app.ui.component.NoDataScreen
import uk.ryanwong.skycatnews.app.ui.component.SkyCatNewsAppBar
import uk.ryanwong.skycatnews.app.ui.theme.SkyCatNewsTheme
import uk.ryanwong.skycatnews.newslist.domain.model.NewsItem
import uk.ryanwong.skycatnews.newslist.ui.screen.component.LargeStoryHeadline
import uk.ryanwong.skycatnews.newslist.ui.screen.component.LargeWebLinkHeadline
import uk.ryanwong.skycatnews.newslist.ui.screen.component.RegularStoryHeadline
import uk.ryanwong.skycatnews.newslist.ui.screen.component.RegularWebLinkHeadline
import uk.ryanwong.skycatnews.newslist.ui.viewmodel.NewsListViewModel
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.ui.screen.previewparameter.NewsListProvider

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun NewsListScreen(
    newsListViewModel: NewsListViewModel = hiltViewModel(),
    navController: NavController,
    onStoryItemClicked: (id: Int) -> Unit,
    onWebLinkItemClicked: (id: Int) -> Unit,
) {
    val uiState by newsListViewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    Box(modifier = Modifier.fillMaxSize()) {
        NewsListScreenLayout(
            newsList = uiState.newsList,
            isLoading = uiState.isLoading,
            onRefresh = { newsListViewModel.refreshNewsList() },
            onStoryItemClicked = onStoryItemClicked,
            onWebLinkItemClicked = onWebLinkItemClicked,
            navController = navController
        )

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

    if (uiState.errorMessages.isNotEmpty()) {
        val errorMessage = remember(uiState) { uiState.errorMessages[0] }
        val errorMessageText = stringResource(errorMessage.messageId)
        val actionLabel = stringResource(R.string.ok)

        LaunchedEffect(errorMessage.id) {
            snackbarHostState.showSnackbar(
                message = errorMessageText,
                actionLabel = actionLabel
            )
            newsListViewModel.errorShown(errorId = errorMessage.id)
        }
    }
}

@Composable
fun NewsListScreenLayout(
    newsList: List<NewsItem>,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    onStoryItemClicked: (id: Int) -> Unit,
    onWebLinkItemClicked: (id: Int) -> Unit,
    navController: NavController,
) {
    val padding16 = dimensionResource(id = R.dimen.padding_16)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
    ) {
        SkyCatNewsAppBar(navController = navController)

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isLoading),
            onRefresh = onRefresh,
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = padding16),
                modifier = Modifier.fillMaxSize()
            ) {
                if (newsList.isEmpty()) {
                    if (!isLoading) {
                        item {
                            NoDataScreen(modifier = Modifier.fillParentMaxHeight())
                        }
                    }
                    return@LazyColumn
                }

                item {
                    val firstItem = newsList.first()
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

                val regularItemsList = newsList.subList(1, newsList.size)
                itemsIndexed(regularItemsList) { _, newsItem ->
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

@Preview(
    name = "News List Screen",
    group = "Light",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
)
@Composable
private fun NewsListScreenPreviewLight(
    @PreviewParameter(NewsListProvider::class)
    newsList: List<NewsItem>,
) {
    SkyCatNewsTheme {
        NewsListScreenLayout(
            newsList = newsList,
            isLoading = false,
            onRefresh = { },
            onStoryItemClicked = {},
            onWebLinkItemClicked = {},
            navController = rememberNavController(),
        )
    }
}

@Preview(
    name = "News List Screen",
    group = "Dark",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun NewsListScreenPreviewDark(
    @PreviewParameter(NewsListProvider::class)
    newsList: List<NewsItem>,
) {
    SkyCatNewsTheme {
        NewsListScreenLayout(
            newsList = newsList,
            isLoading = false,
            onRefresh = { },
            onStoryItemClicked = {},
            onWebLinkItemClicked = {},
            navController = rememberNavController(),
        )
    }
}

@Preview(
    name = "News List Screen No Data",
    group = "Light",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
)
@Composable
private fun NewsListScreenNoDataPreviewLight() {
    SkyCatNewsTheme {
        NewsListScreenLayout(
            newsList = emptyList(),
            isLoading = false,
            onRefresh = { },
            onStoryItemClicked = {},
            onWebLinkItemClicked = {},
            navController = rememberNavController(),
        )
    }
}

@Preview(
    name = "News List Screen No Data",
    group = "Dark",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun NewsListScreenNoDataPreviewDark() {
    SkyCatNewsTheme {
        NewsListScreenLayout(
            newsList = emptyList(),
            isLoading = false,
            onRefresh = { },
            onStoryItemClicked = {},
            onWebLinkItemClicked = {},
            navController = rememberNavController(),
        )
    }
}
