/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ryanwong.skycatnews.R
import uk.ryanwong.skycatnews.app.ui.theme.SkyCatNewsTheme
import uk.ryanwong.skycatnews.newslist.domain.model.NewsItem

@Composable
fun RegularStoryHeadline(
    story: NewsItem.Story,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RegularHeadline(
        imageAccessibilityText = story.teaserImageAccessibilityText,
        headline = story.headline,
        teaserText = story.teaserText,
        date = story.modifiedDate,
        onItemClicked = onItemClicked,
        modifier = modifier
    )
}

@Composable
fun RegularWebLinkHeadline(
    webLink: NewsItem.WebLink,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    RegularHeadline(
        imageAccessibilityText = webLink.teaserImageAccessibilityText,
        headline = webLink.headline,
        teaserText = null,
        date = webLink.modifiedDate,
        onItemClicked = onItemClicked,
        modifier = modifier
    )
}

@Composable
fun RegularHeadline(
    imageAccessibilityText: String?,
    headline: String,
    teaserText: String?,
    date: String,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val padding4 = dimensionResource(id = R.dimen.padding_4)
    val padding16 = dimensionResource(id = R.dimen.padding_16)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                enabled = true,
                onClick = onItemClicked
            )
            .padding(bottom = padding16)
    ) {
        Image(
            painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = imageAccessibilityText,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .wrapContentWidth()
                .height(80.dp)
                .background(color = Color.LightGray)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = headline,
                    maxLines = 1,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .weight(weight = 1.0f, fill = true)
                        .padding(horizontal = padding16, vertical = padding4)
                )
                Text(
                    text = date,
                    maxLines = 1,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(horizontal = padding16, vertical = padding4)
                )
            }
            teaserText?.let {
                Text(
                    text = teaserText,
                    maxLines = 2,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .padding(horizontal = padding16)
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun RegularStoryHeadlinePreview() {
    SkyCatNewsTheme {
        RegularStoryHeadline(
            story = NewsItem.Story(
                newsId = 1,
                headline = "Story Headline 1",
                teaserText = "Breakfast agreeable incommode departure it an. By ignorant at on wondered relation. Enough at tastes really so cousin am of. Extensive therefore supported by extremity of contented. Is pursuit compact demesne invited elderly be. View him she roof tell her case has sigh. Moreover is possible he admitted sociable concerns. By in cold no less been sent hard hill.",
                modifiedDate = "some-date",
                teaserImageUrl = "https://www.google.com/",
                teaserImageAccessibilityText = "some-accessibility-text",
            ),
            onItemClicked = {},
        )
    }
}
