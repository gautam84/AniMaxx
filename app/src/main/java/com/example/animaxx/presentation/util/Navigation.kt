/**
 *
 *	MIT License
 *
 *	Copyright (c) 2023 Gautam Hazarika
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 *
 **/

package com.example.animaxx.presentation.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animaxx.R
import com.example.animaxx.presentation.about.AboutScreen
import com.example.animaxx.presentation.anime_details.AnimeDetailsScreen
import com.example.animaxx.presentation.home.HomeScreen
import com.example.animaxx.presentation.search.SearchScreen
import com.example.animaxx.presentation.starred.StarredScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationGraph(
    navController: NavHostController,
    drawerStateToggle: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(onClick = drawerStateToggle, navController = navController)
        }
        composable(
            route = Screen.AboutScreen.route
        ) {
            AboutScreen()
        }
        composable(
            route = Screen.AnimeDetailScreen.route + "/{animeName}",
            arguments = listOf(
                navArgument(
                    name = "animeName"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val animeName = it.arguments?.getString("sectionName") ?: ""

            AnimeDetailsScreen(
                animeName,
                navController
            )
        }
        composable(
            route = Screen.SearchScreen.route
        ) {
            SearchScreen(drawerStateToggle, navController)
        }
        composable(
            route = Screen.StarredScreen.route
        ) {
            StarredScreen(drawerStateToggle, navController)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavigation(
    isChecked: Boolean,
    changeState: (isDark: Boolean) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val list = listOf(
        Screen.HomeScreen.route,
        Screen.SearchScreen.route,
        Screen.StarredScreen.route,
        Screen.AboutScreen.route
    )

    val getIcon: (String) -> ImageVector = {
        when (it) {
            Screen.HomeScreen.route -> Icons.Outlined.Home
            Screen.SearchScreen.route -> Icons.Outlined.Search
            Screen.StarredScreen.route -> Icons.Outlined.Star
            Screen.AboutScreen.route -> Icons.Outlined.Info


            else -> Icons.Outlined.Info
        }

    }

    val getDisplayName: (String) -> String = {
        when (it) {
            Screen.HomeScreen.route -> "Home"
            Screen.SearchScreen.route -> "Search"
            Screen.StarredScreen.route -> "Starred"
            Screen.AboutScreen.route -> "About"
            else -> "None"
        }

    }


    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_animaxx),
                    contentDescription = null
                )
                Text(
                    text = "All you need to know about anime",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            list.forEach {
                NavigationDrawerItem(
                    icon = { Icon(getIcon(it), contentDescription = null) },
                    label = { Text(text = getDisplayName(it)) },
                    selected = currentRoute == it,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(it)
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(Icons.Outlined.Visibility, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Dark Mode")
                }

                Switch(
                    checked = isChecked,
                    onCheckedChange = {
                        changeState(it)
                    })
            }

        }


    }) {
        NavigationGraph(navController) {
            scope.launch {
                if (drawerState.isOpen) {
                    drawerState.close()
                } else {
                    drawerState.open()
                }
            }

        }
    }
}