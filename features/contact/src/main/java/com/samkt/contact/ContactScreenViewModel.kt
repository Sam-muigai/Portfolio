package com.samkt.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.domain.helpers.Result
import com.samkt.domain.models.SocialMedia
import com.samkt.domain.repositories.UserRepository
import com.samkt.domain.utils.Constants.USER_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ContactScreenViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _contactScreenUiState =
        MutableStateFlow<ContactScreenUiState>(ContactScreenUiState.Loading)
    val contactScreenUiState = _contactScreenUiState.asStateFlow()


    init {
        getSocialMediaAccounts()
    }

    private fun getSocialMediaAccounts() {
        viewModelScope.launch {
            when (val result = userRepository.getSocialMediaInformation(USER_ID)) {
                is Result.Error -> {
                    _contactScreenUiState.update { ContactScreenUiState.Error(result.message) }
                }

                is Result.Success -> {
                    _contactScreenUiState.update { ContactScreenUiState.Success(result.data) }
                }
            }
        }
    }
}


sealed interface ContactScreenUiState {
    data object Loading : ContactScreenUiState
    data class Success(val socialMedia: SocialMedia) : ContactScreenUiState
    data class Error(val message: String) : ContactScreenUiState
}

fun String.formatLink(): String {
    return if (!this.startsWith("http://") && !this.startsWith("https://")) {
        "https://$this"
    } else {
        this
    }
}