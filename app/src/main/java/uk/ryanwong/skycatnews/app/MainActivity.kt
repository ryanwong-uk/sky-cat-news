/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import uk.ryanwong.skycatnews.app.ui.theme.SkyCatNewsTheme
import uk.ryanwong.skycatnews.newslist.ui.screen.NewsListScreen
import uk.ryanwong.skycatnews.newslist.ui.screen.WebLinkScreen
import uk.ryanwong.skycatnews.storydetail.ui.screen.StoryDetailScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkyCatNewsTheme {
                SkyCatNewsApp()
            }
        }
    }
}

@Composable
private fun SkyCatNewsApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "newslist") {
        composable(route = "newslist") {
            NewsListScreen(
                onStoryItemClicked = { listId -> navController.navigate("newslist/story/$listId") },
                onWebLinkItemClicked = { listId -> navController.navigate("newslist/weblink/$listId") },
            )
        }
        composable(
            route = "newslist/story/{list_id}",
            arguments = listOf(
                navArgument("list_id") {
                    type = NavType.IntType
                }
            )
        ) {
            StoryDetailScreen()
        }

        composable(
            route = "newslist/weblink/{list_id}",
            arguments = listOf(
                navArgument("list_id") {
                    type = NavType.IntType
                }
            )
        ) {
            WebLinkScreen()
        }
    }
}