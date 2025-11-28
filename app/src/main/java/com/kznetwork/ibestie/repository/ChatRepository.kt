package com.kznetwork.ibestie.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kznetwork.ibestie.data.local.SecurePreferences
import com.kznetwork.ibestie.model.ChatMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for chat messages
 */
class ChatRepository(
    private val securePrefs: SecurePreferences = SecurePreferences()
) {
    private val gson = Gson()

    suspend fun saveMessage(message: ChatMessage) = withContext(Dispatchers.IO) {
        val messages = getMessages().toMutableList()
        messages.add(message)
        saveMessages(messages)
    }

    suspend fun getMessages(): List<ChatMessage> = withContext(Dispatchers.IO) {
        val json = securePrefs.getString(KEY_MESSAGES) ?: return@withContext emptyList()
        try {
            val type = object : TypeToken<List<ChatMessage>>() {}.type
            gson.fromJson<List<ChatMessage>>(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun clearMessages() = withContext(Dispatchers.IO) {
        securePrefs.remove(KEY_MESSAGES)
    }

    private fun saveMessages(messages: List<ChatMessage>) {
        val json = gson.toJson(messages)
        securePrefs.saveString(KEY_MESSAGES, json)
    }

    companion object {
        private const val KEY_MESSAGES = "chat_messages"
    }
}

