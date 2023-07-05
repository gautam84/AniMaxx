package com.example.animaxx.presentation.search

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.animaxx.domain.model.Anime
import com.example.animaxx.presentation.common.components.AnimeDescriptionCard

@Composable
fun SearchScreen(
    onClick: () -> Unit,
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.searchScreenState.value
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        item {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
                SearchBar(
                    state.text
                ) { viewModel.onEvent(SearchEvent.ChangeText(it)) }

                Box(modifier = Modifier
                    .clickable {
                        Toast.makeText(context,"Feature coming soon..", Toast.LENGTH_SHORT).show()
                    }
                    .border(width = 1.dp, Color.Black, RoundedCornerShape(12.dp))) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp),
                        tint = Color.Black
                    )

                }
            }
        }

        if (state.text != "" && state.text != " ") {
            items(state.searchedList.size) {
                AnimeDescriptionCard(state.searchedList[it], navController )

            }
        } else {
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Recently Searched..", style = MaterialTheme.typography.labelMedium)
                }
            }
            items(state.recentlySearchedList.size) {

                AnimeDescriptionCard(state.recentlySearchedList[it], navController)

            }
        }


    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    state: String, event: (text: String) -> Unit

) {

    TextField(
        value = state,
        onValueChange = { event(it) },
        placeholder = {
            Text(
                text = "Search", modifier = Modifier.alpha(0.5f)
            )
        },
        leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search") },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .border(width = 1.dp, Color.Black, shape = CircleShape),

        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            cursorColor = Color.White,

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        singleLine = true
    )
}