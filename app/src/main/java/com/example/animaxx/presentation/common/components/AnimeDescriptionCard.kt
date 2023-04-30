package com.example.animaxx.presentation.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.animaxx.domain.model.Anime
import com.example.animaxx.presentation.util.Screen

@Composable
fun AnimeDescriptionCard(
    anime: Anime,
    navController: NavHostController
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable {
                    navController.navigate(Screen.AnimeDetailScreen.withArgs(anime.name))
                }
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = anime.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(24.dp)),

                )
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.height(200.dp)
            ) {
                Text(text = anime.name, color = Color.White)
                Text(
                    text = "Genre: ${anime.genre}",
                    color = Color.White.copy(0.5f),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Stars: ${anime.actors}",
                    color = Color.White.copy(0.5f),
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = anime.description,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelSmall

                )


            }

        }
    }
}