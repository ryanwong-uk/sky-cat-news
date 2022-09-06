/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ryanwong.skycatnews.R
import uk.ryanwong.skycatnews.app.ui.theme.SkyCatNewsTheme
import uk.ryanwong.skycatnews.newslist.domain.model.NewsItem

@Composable
fun LargeStoryHeadline(
    story: NewsItem.Story,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LargeHeadline(
        imageAccessibilityText = story.teaserImageAccessibilityText,
        headline = story.headline,
        teaserText = story.teaserText,
        date = story.modifiedDate,
        onItemClicked = onItemClicked,
        modifier = modifier
    )
}

@Composable
fun LargeWebLinkHeadline(
    webLink: NewsItem.WebLink,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LargeHeadline(
        imageAccessibilityText = webLink.teaserImageAccessibilityText,
        headline = webLink.headline,
        teaserText = null,
        date = webLink.modifiedDate,
        onItemClicked = onItemClicked,
        modifier = modifier
    )
}

@Composable
fun LargeHeadline(
    imageAccessibilityText: String?,
    headline: String,
    teaserText: String?,
    date: String,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val padding4 = dimensionResource(id = R.dimen.padding_4)
    val padding16 = dimensionResource(id = R.dimen.padding_16)

    Card(
        modifier = modifier
            .padding(horizontal = padding16)
            .padding(bottom = padding16)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    enabled = true,
                    onClick = onItemClicked
                )
        ) {
            // TODO: Coil
            Image(
                painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
                contentDescription = imageAccessibilityText,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .background(color = Color.LightGray)
            )

            Text(
                text = headline,
                maxLines = 1,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = padding16, vertical = padding4)
            )
            teaserText?.let {
                Text(
                    text = teaserText,
                    maxLines = 3,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = padding16)
                )
            }
            Text(
                text = date,
                maxLines = 1,
                style = MaterialTheme.typography.caption,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = padding16, vertical = padding4)
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun LargeStoryHeadlinePreview() {
    SkyCatNewsTheme {
        LargeStoryHeadline(
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
