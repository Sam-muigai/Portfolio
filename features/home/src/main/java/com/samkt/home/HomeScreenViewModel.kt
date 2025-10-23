package com.samkt.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.domain.helpers.Result
import com.samkt.domain.models.UserInformation
import com.samkt.domain.repositories.UserRepository
import com.samkt.domain.utils.Constants.USER_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _homeScreenUiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading)
    val homeScreenUiState = _homeScreenUiState.asStateFlow()

    init {
        loadUserInformation()
    }

    private fun loadUserInformation() {
        viewModelScope.launch {
            when (val result = userRepository.getUserInformation(USER_ID)) {
                is Result.Error -> _homeScreenUiState.update { HomeScreenUiState.Error(result.message) }
                is Result.Success -> _homeScreenUiState.update { HomeScreenUiState.Success(result.data) }
            }
        }
    }
}

sealed class HomeScreenUiState {
    data object Loading : HomeScreenUiState()
    data class Success(val userInformation: UserInformation) : HomeScreenUiState()
    data class Error(val message: String) : HomeScreenUiState()
}
