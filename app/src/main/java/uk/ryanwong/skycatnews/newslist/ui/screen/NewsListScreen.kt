/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.ryanwong.skycatnews.app.ui.theme.SkyCatNewsTheme

@Composable
fun NewsListScreen(
    onStoryItemClicked: (id: Int) -> Unit,
    onWebLinkItemClicked: (id: Int) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Button(onClick = { onStoryItemClicked(1) }) {
            Text(text = "Story 1")
        }

        Button(onClick = { onWebLinkItemClicked(1) }) {
            Text(text = "Weblink 1")
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
