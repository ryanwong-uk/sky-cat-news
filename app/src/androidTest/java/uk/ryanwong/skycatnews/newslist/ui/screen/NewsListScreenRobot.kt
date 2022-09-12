/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.newslist.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import uk.ryanwong.skycatnews.app.ui.SkyCatNewsTestRule

internal class NewsListScreenRobot(
    private val composeTestRule: SkyCatNewsTestRule,
) {

    fun waitUntilNewsListIsVisible() {
        with(composeTestRule) {
            waitUntil(timeoutMillis = 3000) {
                onNodeWithContentDescription(label = "News list").onChildren()
                    .fetchSemanticsNodes().size > 1
            }
        }
    }

    fun scrollToStoryHeadline1() {
        with(composeTestRule) {
            onNodeWithContentDescription(label = "News list").performScrollToIndex(0)
            waitForIdle()
        }
    }

    fun storyHeadline1ShowsCorrectLayout() {
        with(composeTestRule) {
            onNodeWithContentDescription(label = "News list").performScrollToIndex(0)
            waitForIdle()

            onNodeWithText(text = "some-story-headline-1").assertIsDisplayed()
            onNodeWithText(text = "some-teaser-text-1").assertIsDisplayed()
            onNodeWithText(text = "Yesterday").assertIsDisplayed()
            onNodeWithContentDescription(label = "some-teaser-image-accessibility-text-1").assertIsDisplayed()
        }
    }

    fun storyHeadline2ShowsCorrectLayout() {
        with(composeTestRule) {
            onNodeWithContentDescription(label = "News list").performScrollToIndex(1)
            waitForIdle()

            onNodeWithText(text = "some-story-headline-2").assertIsDisplayed()
            onNodeWithText(text = "some-teaser-text-2").assertIsDisplayed()
            onNodeWithText(text = "2 days ago").assertIsDisplayed()
            onNodeWithContentDescription(label = "some-teaser-image-accessibility-text-2").assertIsDisplayed()
        }
    }

    fun weblinkHeadline3ShowsCorrectLayout() {
        with(composeTestRule) {
            onNodeWithContentDescription(label = "News list").performScrollToIndex(2)
            waitForIdle()

            onNodeWithText(text = "some-weblink-headline-3").assertIsDisplayed()
            onNodeWithText(text = "3 days ago").assertIsDisplayed()
            onNodeWithContentDescription(label = "some-teaser-image-accessibility-text-3").assertIsDisplayed()
        }
    }

    fun storyHeadline4ShowsCorrectLayout() {
        with(composeTestRule) {
            onNodeWithContentDescription(label = "News list").performScrollToIndex(3)
            waitForIdle()

            onNodeWithText(text = "some-story-headline-4").assertIsDisplayed()
            onNodeWithText(text = "some-teaser-text-4").assertIsDisplayed()
            onNodeWithText(text = "4 days ago").assertIsDisplayed()
            onNodeWithContentDescription(label = "some-teaser-image-accessibility-text-4").assertIsDisplayed()
        }
    }

    fun weblinkHeadline5ShowsCorrectLayout() {
        with(composeTestRule) {
            onNodeWithContentDescription(label = "News list").performScrollToIndex(4)
            waitForIdle()

            onNodeWithText(text = "some-weblink-headline-5").assertIsDisplayed()
            onNodeWithText(text = "5 days ago").assertIsDisplayed()
            onNodeWithContentDescription(label = "some-teaser-image-accessibility-text-5").assertIsDisplayed()
        }
    }

    fun storyHeadline6ShowsCorrectLayout() {
        with(composeTestRule) {
            onNodeWithContentDescription(label = "News list").performScrollToIndex(5)
            waitForIdle()

            onNodeWithText(text = "some-story-headline-6").assertIsDisplayed()
            onNodeWithText(text = "some-teaser-text-6").assertIsDisplayed()
            onNodeWithText(text = "6 days ago").assertIsDisplayed()
            onNodeWithContentDescription(label = "some-teaser-image-accessibility-text-6").assertIsDisplayed()
        }
    }

    fun userClicksOnStoryHeadline1() {
        with(composeTestRule) {
            onNodeWithContentDescription(label = "News list").performScrollToIndex(0).run {
                waitForIdle()
                performClick()
                waitForIdle()
            }
        }
    }

    fun storyScreenShowsCorrectLayout() {
        with(composeTestRule) {
            onNodeWithText(text = "some-headline").assertIsDisplayed()
            onNodeWithContentDescription(label = "some-hero-image-accessibility-text").assertIsDisplayed()
            onNodeWithText(text = "some-paragraph-1").assertIsDisplayed()
            onNodeWithContentDescription(label = "some-accessibility-text-2").assertIsDisplayed()
            onNodeWithText(text = "some-paragraph-3").assertIsDisplayed()
        }
    }

}