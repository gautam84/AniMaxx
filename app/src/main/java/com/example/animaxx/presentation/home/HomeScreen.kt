package com.example.animaxx.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.animaxx.R
import com.example.animaxx.domain.model.Anime
import com.example.animaxx.presentation.util.Screen

@Composable
fun HomeScreen(
    navController: NavHostController,
    onClick: () -> Unit, viewModel: HomeViewModel = hiltViewModel()

) {
    val topRatedList = viewModel.homeScreenState.value.topRatedList
    val recentlySearchedList = viewModel.homeScreenState.value.recentlySearchedList
    val exploreList = viewModel.homeScreenState.value.exploreList


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.attack_on_titan_banner),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable {
                            onClick()
                        }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp),
                            tint = Color.White
                        )

                    }
                    Spacer(modifier = Modifier.height(110.dp))

                    Row {

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.5f))
                                .padding(8.dp)
                        ) {

                            Text(
                                text = "Currently trending..",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(24.dp))

        }

        item {
            AnimeRow(title = "Top Rated..", topRatedList, navController)
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            AnimeRow(title = "Recently searched..", recentlySearchedList, navController)
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            AnimeRow(
                title = "Explore..", exploreList, navController            )
            Spacer(modifier = Modifier.height(24.dp))
        }


    }
}


@Composable
fun AnimeRow(
    title: String, list: List<Anime>,

    navController: NavHostController
) {
    Column {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Color.Black.copy(alpha = 0.3f))
                .padding(8.dp)
        ) {
            Text(text = title, color = Color.White)
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.3f)),
            contentPadding = PaddingValues(
                16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            items(list.size) {
                AnimeCard(anime = list[it], navController = navController)
            }
        }
    }

}

@Composable
fun AnimeCard(anime: Anime, navController: NavHostController) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(24.dp))
        .clickable {
            navController.navigate(Screen.AnimeDetailScreen.withArgs(anime.name))
        }) {
        Image(
            painter = painterResource(id = anime.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .width(150.dp)
                .clip(RoundedCornerShape(24.dp)),

            )
        Box(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(4.dp)


            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFB800)
                )
                Text(text = anime.stars.toString(), color = Color.White)
            }
        }

    }

}