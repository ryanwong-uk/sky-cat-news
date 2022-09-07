/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen.component

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uk.ryanwong.skycatnews.R
import uk.ryanwong.skycatnews.app.ui.theme.CustomTextStyle
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
        date = story.getNiceDate(),
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
        date = webLink.getNiceDate(),
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
                .background(color = MaterialTheme.colors.primary)
                .fillMaxWidth()
                .wrapContentHeight()
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
                fallback = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = imageAccessibilityText,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Text(
                text = headline,
                maxLines = 1,
                style = CustomTextStyle.largeHeadline,
                color = MaterialTheme.colors.onPrimary,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = padding16, vertical = padding8)
            )

            teaserText?.let {
                Text(
                    text = teaserText,
                    maxLines = 3,
                    style = CustomTextStyle.largeHeadlineTeaserText,
                    color = MaterialTheme.colors.onPrimary,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = padding16)
                )
            }

            Text(
                text = date,
                maxLines = 1,
                style = CustomTextStyle.largeHeadlineDate,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = padding16, vertical = padding8)
            )
        }
    }
}

@Preview(
    name = "Large Story Headline",
    showBackground = true,
    heightDp = 320,
)
@Composable
fun LargeStoryHeadlinePreview() {
    SkyCatNewsTheme {
        LargeStoryHeadline(
            story = NewsItem.Story(
                newsId = 1,
                headline = "Story Headline 1",
                teaserText = "Breakfast agreeable incommode departure it an. By ignorant at on wondered relation. Enough at tastes really so cousin am of. Extensive therefore supported by extremity of contented. Is pursuit compact demesne invited elderly be. View him she roof tell her case has sigh. Moreover is possible he admitted sociable concerns. By in cold no less been sent hard hill.",
                modifiedDate = "2022-09-06T00:00:00Z",
                teaserImageUrl = "https://ryanwong.co.uk/sample-resources/skycatnews/cat1_hero.jpg",
                teaserImageAccessibilityText = "some-accessibility-text",
            ),
            onItemClicked = {},
        )
    }
}

@Preview(
    name = "Large WebLink Headline",
    showBackground = true,
    heightDp = 320,
)
@Composable
fun LargeWebLinkHeadlinePreview() {
    SkyCatNewsTheme {
        LargeWebLinkHeadline(
            webLink = NewsItem.WebLink(
                newsId = 1,
                headline = "Story Headline 1",
                url = "https://some.url/",
                modifiedDate = "2022-09-06T00:00:00Z",
                teaserImageUrl = "https://ryanwong.co.uk/sample-resources/skycatnews/cat1_hero.jpg",
                teaserImageAccessibilityText = "some-accessibility-text",
            ),
            onItemClicked = {},
        )
    }
}
