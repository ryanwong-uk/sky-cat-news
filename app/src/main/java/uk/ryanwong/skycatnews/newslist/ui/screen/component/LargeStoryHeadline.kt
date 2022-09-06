/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
        imageUrl = story.teaserImageUrl,
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
        imageUrl = webLink.teaserImageUrl,
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
    imageUrl: String?,
    imageAccessibilityText: String?,
    headline: String,
    teaserText: String?,
    date: String,
    onItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val padding8 = dimensionResource(id = R.dimen.padding_8)
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
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = imageAccessibilityText,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Text(
                text = headline,
                maxLines = 1,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = padding16, vertical = padding8)
            )
            teaserText?.let {
                Text(
                    text = teaserText,
                    maxLines = 3,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
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
                    .padding(horizontal = padding16, vertical = padding8)
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
