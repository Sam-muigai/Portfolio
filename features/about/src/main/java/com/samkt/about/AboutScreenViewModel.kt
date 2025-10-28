package com.samkt.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.domain.helpers.Result
import com.samkt.domain.models.AboutMe
import com.samkt.domain.useCase.GetAboutUseCase
import com.samkt.domain.utils.Constants.USER_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AboutScreenViewModel(
  private val getAboutUseCase: GetAboutUseCase,
) : ViewModel() {

  private val _aboutScreenUiState =
    MutableStateFlow<AboutScreenUiState>(AboutScreenUiState.Loading)
  val aboutScreenUiState = _aboutScreenUiState.asStateFlow()

  init {
    getAbout()
  }

  private fun getAbout() {
    viewModelScope.launch {
      when (val response = getAboutUseCase.invoke(USER_ID)) {
        is Result.Error -> {
          _aboutScreenUiState.update { AboutScreenUiState.Error(response.message) }
        }

        is Result.Success -> {
          _aboutScreenUiState.update { AboutScreenUiState.Success(response.data) }
        }
      }
    }
  }
}

sealed interface AboutScreenUiState {
  data class Success(val aboutMe: AboutMe) : AboutScreenUiState
  data class Error(val message: String) : AboutScreenUiState
  data object Loading : AboutScreenUiState
}
