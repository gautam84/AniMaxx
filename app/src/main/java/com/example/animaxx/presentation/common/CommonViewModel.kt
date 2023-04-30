package com.example.animaxx.presentation.common

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animaxx.domain.repository.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CommonViewModel @Inject constructor(
   private val repository: MainRepository
) : ViewModel() {
    private val _state = mutableStateOf(DarkThemeState())
    val state: State<DarkThemeState> = _state

    init {

        viewModelScope.launch{
 repository.isAppDark.collect{
     _state.value = state.value.copy(
            isSystemDark =it
     )
 }
        }
    }

    fun onEvent(event: ThemeEvent){
        when(event){
            is ThemeEvent.ChangeTheme ->{
                viewModelScope.launch {
                    repository.toggleLoginState()
                }
            }
        }
    }


}
