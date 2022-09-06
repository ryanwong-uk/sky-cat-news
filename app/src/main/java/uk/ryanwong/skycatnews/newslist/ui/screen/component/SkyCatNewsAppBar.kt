/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uk.ryanwong.skycatnews.R
import uk.ryanwong.skycatnews.app.ui.theme.SkyCatNewsTheme
import uk.ryanwong.skycatnews.app.ui.theme.SkyGreyBackground

@Composable
fun SkyCatNewsAppBar(
    customTitle: String? = null,
    modifier: Modifier = Modifier,
) {
    val padding8 = dimensionResource(id = R.dimen.padding_8)

    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                customTitle?.let {
                    Text(
                        text = customTitle,
                        style = MaterialTheme.typography.h6,
                    )
                } ?: Image(
                    painter = painterResource(id = R.drawable.header_skycatnews),
                    contentDescription = stringResource(R.string.app_name),
                    alignment = Alignment.Center,
                    modifier = Modifier.padding(vertical = padding8),
                )
            }
        },
        backgroundColor = SkyGreyBackground,
        modifier = modifier
    )
}

@Preview(
    name = "Default image app bar",
    showBackground = true
)
@Composable
private fun AppBarPreviewDefaultImage() {
    SkyCatNewsTheme {
        SkyCatNewsAppBar(
            customTitle = null
        )
    }
}

@Preview(
    name = "Custom title app bar",
    showBackground = true
)
@Composable
private fun AppBarPreviewCustomTitle() {
    SkyCatNewsTheme {
        SkyCatNewsAppBar(
            customTitle = "Some Other News"
        )
    }
}
