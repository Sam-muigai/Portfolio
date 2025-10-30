package com.samkt.domain.repositories

import com.samkt.domain.helpers.Result
import com.samkt.domain.models.Contact

interface MessageRepository {
  suspend fun sendMessage(contact: Contact): Result<String>
}
