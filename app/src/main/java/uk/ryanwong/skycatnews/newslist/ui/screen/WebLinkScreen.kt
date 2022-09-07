/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import uk.ryanwong.skycatnews.app.ui.component.SkyCatNewsAppBar

@Composable
fun WebLinkScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val webViewState = rememberWebViewState(url = "https://www.sky.co.uk/")

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        SkyCatNewsAppBar(navController = navController)

        WebView(
            state = webViewState,
            onCreated = { it.settings.javaScriptEnabled = true },
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview
@Composable
private fun WeblinkScreenPreview() {
    WebLinkScreen(
        navController = rememberNavController(),
    )
}
