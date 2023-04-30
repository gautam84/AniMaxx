package com.example.animaxx.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.animaxx.presentation.common.CommonViewModel
import com.example.animaxx.presentation.common.ThemeEvent
import com.example.animaxx.presentation.util.SetupNavigation
import com.example.animaxx.ui.theme.AniMaxxTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: CommonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniMaxxTheme(
                dynamicColor = false,
                darkTheme = isSystemInDarkTheme() || viewModel.state.value.isSystemDark
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavigation(
                        isChecked = viewModel.state.value.isSystemDark,
                        changeState = {
                            viewModel.onEvent(ThemeEvent.ChangeTheme)

                        }

                    )

                }
            }
        }
    }
}

