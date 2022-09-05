/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun WebLinkScreen(
    storyId: Int,
    modifier: Modifier = Modifier,
) {
    val webViewState = rememberWebViewState("https://www.rwmobi.com/")

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("text = header",
                modifier = Modifier.height(48.dp))
        }
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
        storyId = 1
    )
}
