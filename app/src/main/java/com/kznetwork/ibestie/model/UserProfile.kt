package com.kznetwork.ibestie.model

/**
 * User profile data collected during onboarding
 */
data class UserProfile(
    val name: String = "",
    val preferredName: String = "",
    val age: Int? = null,
    val interests: List<String> = emptyList(),
    val conversationStyle: ConversationStyle = ConversationStyle.BALANCED,
    val privacyConsent: Boolean = false,
    val voiceEnabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)

enum class ConversationStyle {
    CASUAL,      // More relaxed, informal
    BALANCED,    // Mix of casual and supportive
    SUPPORTIVE   // More empathetic, therapeutic
}

