/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.kotest.inspectors.runTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.ryanwong.skycatnews.app.MainActivity

@kotlinx.coroutines.ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
internal class NewsListScreenKtTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var newsListScreenRobot: NewsListScreenRobot

    @Before
    fun setUp() {
        runTest {
            hiltRule.inject()

            newsListScreenRobot = NewsListScreenRobot(composeTestRule)
        }
    }

    @Test
    fun news_list_screen_general_layout_is_correct() {
        with(newsListScreenRobot) {
            // given
            with(composeTestRule) {
                waitForIdle()
                onNodeWithText(text = "Header")
                    .performClick()
                waitForIdle()
            }
        }
    }
}