/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import uk.ryanwong.skycatnews.app.ui.component.SkyCatNewsAppBar
import uk.ryanwong.skycatnews.app.ui.theme.SkyCatNewsTheme
import uk.ryanwong.skycatnews.newslist.ui.screen.NewsListScreen
import uk.ryanwong.skycatnews.storydetail.ui.screen.StoryDetailScreen
import uk.ryanwong.skycatnews.weblink.ui.screen.WebLinkScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()

            SkyCatNewsTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = { SkyCatNewsAppBar(navController = navController) },
                ) { padding ->
                    SkyCatNewsApp(
                        navController = navController,
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }
}

@Composable
private fun SkyCatNewsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    NavHost(navController = navController, startDestination = "newslist") {
        composable(route = "newslist") {
            NewsListScreen(
                modifier = modifier,
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
            StoryDetailScreen(modifier = modifier)
        }

        composable(
            route = "newslist/weblink/{list_id}",
            arguments = listOf(
                navArgument("list_id") {
                    type = NavType.IntType
                }
            )
        ) {
            WebLinkScreen(modifier = modifier)
        }
    }
}
