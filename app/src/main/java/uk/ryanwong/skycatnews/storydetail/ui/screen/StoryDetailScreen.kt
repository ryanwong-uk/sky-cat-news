/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.storydetail.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ryanwong.skycatnews.newslist.ui.screen.component.SkyCatNewsAppBar

@Composable
fun StoryDetailScreen(storyId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        SkyCatNewsAppBar()
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(30) {
                Text(
                    text = "Greg is great",
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
