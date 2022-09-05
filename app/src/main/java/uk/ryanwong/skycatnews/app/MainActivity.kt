/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import uk.ryanwong.skycatnews.app.ui.theme.SkyCatNewsTheme
import uk.ryanwong.skycatnews.newslist.ui.screen.WebLinkScreen
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.newslist.ui.screen.NewsListScreen
import uk.ryanwong.skycatnews.uk.ryanwong.skycatnews.storydetail.ui.screen.StoryDetailScreen

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
                onStoryItemClicked = { id -> navController.navigate("newslist/story/$id") },
                onWebLinkItemClicked = { id -> navController.navigate("newslist/weblink/$id") },
            )
        }
        composable(
            route = "newslist/story/{story_id}",
            arguments = listOf(
                navArgument("story_id") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val storyId = navBackStackEntry.arguments?.getInt("story_id")
            storyId?.let {
                StoryDetailScreen(storyId = storyId)
            }
        }

        composable(
            route = "newslist/weblink/{story_id}",
            arguments = listOf(
                navArgument("story_id") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val storyId = navBackStackEntry.arguments?.getInt("story_id")
            storyId?.let {
                WebLinkScreen(storyId = storyId)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SkyCatNewsTheme {
        SkyCatNewsApp()
    }
}
