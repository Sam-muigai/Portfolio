package com.samkt.contact

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.domain.helpers.Result
import com.samkt.domain.models.Contact
import com.samkt.domain.models.SocialMedia
import com.samkt.domain.repositories.MessageRepository
import com.samkt.domain.repositories.UserRepository
import com.samkt.domain.utils.Constants.USER_ID
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactScreenViewModel(
  private val userRepository: UserRepository,
  private val messageRepository: MessageRepository,
) : ViewModel() {

  private val _contactScreenUiState =
    MutableStateFlow<ContactScreenUiState>(ContactScreenUiState.Loading)
  val contactScreenUiState = _contactScreenUiState.asStateFlow()

  var email by mutableStateOf("")
    private set

  var name by mutableStateOf("")
    private set

  var message by mutableStateOf("")
    private set

  var isSendingMessage by mutableStateOf(false)
    private set

  private val _responseMessage = Channel<String>()
  val responseMessage = _responseMessage.receiveAsFlow()

  fun onEmailChange(email: String) {
    this.email = email
  }

  fun onNameChange(name: String) {
    this.name = name
  }

  fun onMessageChange(message: String) {
    this.message = message
  }

  fun onSendMessage() {
    isSendingMessage = true
    viewModelScope.launch {
      val contact = Contact(
        name = name,
        email = email,
        message = message,
      )
      when (val result = messageRepository.sendMessage(contact)) {
        is Result.Error -> {
          isSendingMessage = false
          _responseMessage.send(result.message)
        }
        is Result.Success -> {
          isSendingMessage = false
          _responseMessage.send(result.data)
        }
      }
    }
  }

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
