package com.kznetwork.ibestie.model

/**
 * Represents a single chat message
 */
data class ChatMessage(
    val id: String = "",
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val emotion: Emotion? = null,
    val audioPath: String? = null
)

enum class Emotion {
    HAPPY,
    SAD,
    ANXIOUS,
    EXCITED,
    NEUTRAL,
    CONFUSED,
    ANGRY
}

