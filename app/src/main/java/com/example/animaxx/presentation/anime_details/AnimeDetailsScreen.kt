package com.example.animaxx.presentation.anime_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun AnimeDetailsScreen(
    animeName: String,
    navHostController: NavHostController,
    viewModel: AnimeDetailsViewModel = hiltViewModel(),
) {
    val currAnime = viewModel.animeDetailState.value.currAnime
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Box {
                Image(
                    painter = painterResource(id = currAnime.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickable {
                                navHostController.navigateUp()
                            }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                modifier = Modifier.padding(8.dp),
                                tint = Color.White
                            )

                        }
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickable {
                                viewModel.onEvent(AnimeDetailEvent.ToggleLikedState)
                            }) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                modifier = Modifier.padding(8.dp),
                                tint = if (viewModel.animeDetailState.value.isFavourite) Color(
                                    0xFFEA6B24
                                ) else Color.White.copy(0.5f)
                            )

                        }
                    }


                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(8.dp)) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.Black.copy(alpha = 0.5f))
                        .padding(16.dp)
                ) {


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = currAnime.name,
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Black.copy(alpha = 0.6f))
                                .padding(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color(
                                    0xFFEA6B24
                                )
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = currAnime.stars.toString(), color = Color.White)
                        }
                    }
                    Text(
                        text = "Genre: ${currAnime.genre}",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White.copy(0.5f),
                    )
                    Text(
                        text = "Stars: ${currAnime.actors}",
                        style = MaterialTheme.typography.labelLarge,

                        color = Color.White.copy(0.5f),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = currAnime.description,
                        color = Color.White,

                        )


                }
            }
        }

    }
}