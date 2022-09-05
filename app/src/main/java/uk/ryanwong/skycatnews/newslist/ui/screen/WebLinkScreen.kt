/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebLinkScreen(
    storyId: Int,
    modifier: Modifier = Modifier,
) {
    val url = "https://www.rwmobi.com/"
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                with(settings) {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                }
                loadUrl(url)
            }
        },
        update = { webView -> webView.loadUrl(url) },
        modifier = modifier.fillMaxSize()
    )
}

@Preview
@Composable
private fun WeblinkScreenPreview() {
    WebLinkScreen(
        storyId = 1
    )
}
