package com.kznetwork.ibestie.viewmodel

import com.kznetwork.ibestie.model.ChatMessage
import com.kznetwork.ibestie.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for chat screen
 * TODO: Integrate SpeechManager for STT/TTS
 * TODO: Integrate with FastAPI backend for GPT responses
 */
class ChatViewModel(
    private val chatRepository: ChatRepository = ChatRepository()
) : BaseViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening.asStateFlow()

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    init {
        loadMessages()
    }

    private fun loadMessages() {
        launchSafe {
            _messages.value = chatRepository.getMessages()
        }
    }

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        launchSafe {
            // Add user message
            val userMessage = ChatMessage(
                id = System.currentTimeMillis().toString(),
                text = text,
                isUser = true
            )
            _messages.value = _messages.value + userMessage
            chatRepository.saveMessage(userMessage)

            // TODO: Send to FastAPI backend and get response
            // For now, placeholder response
            val response = ChatMessage(
                id = (System.currentTimeMillis() + 1).toString(),
                text = "I hear you. Tell me more about that.",
                isUser = false
            )
            _messages.value = _messages.value + response
            chatRepository.saveMessage(response)
        }
    }

    fun startListening() {
        _isListening.value = true
        // TODO: Implement STT with Vosk/Google
    }

    fun stopListening() {
        _isListening.value = false
        // TODO: Stop STT and process result
    }

    fun startSpeaking(text: String) {
        _isSpeaking.value = true
        // TODO: Implement TTS
    }

    fun stopSpeaking() {
        _isSpeaking.value = false
        // TODO: Stop TTS
    }

    fun clearMessages() {
        launchSafe {
            chatRepository.clearMessages()
            _messages.value = emptyList()
        }
    }
}

