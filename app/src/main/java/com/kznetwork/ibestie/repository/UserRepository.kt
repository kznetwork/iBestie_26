package com.kznetwork.ibestie.repository

import com.kznetwork.ibestie.data.local.SecurePreferences
import com.kznetwork.ibestie.model.UserProfile
import com.kznetwork.ibestie.model.ConversationStyle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for user profile data
 */
class UserRepository(
    private val securePrefs: SecurePreferences = SecurePreferences()
) {

    suspend fun saveUserProfile(profile: UserProfile) = withContext(Dispatchers.IO) {
        securePrefs.saveString(KEY_NAME, profile.name)
        securePrefs.saveString(KEY_PREFERRED_NAME, profile.preferredName)
        profile.age?.let { securePrefs.saveInt(KEY_AGE, it) }
        securePrefs.saveStringSet(KEY_INTERESTS, profile.interests.toSet())
        securePrefs.saveString(KEY_CONVERSATION_STYLE, profile.conversationStyle.name)
        securePrefs.saveBoolean(KEY_PRIVACY_CONSENT, profile.privacyConsent)
        securePrefs.saveBoolean(KEY_VOICE_ENABLED, profile.voiceEnabled)
        securePrefs.saveLong(KEY_CREATED_AT, profile.createdAt)
        securePrefs.saveBoolean(KEY_ONBOARDING_COMPLETE, true)
    }

    suspend fun getUserProfile(): UserProfile? = withContext(Dispatchers.IO) {
        if (!isOnboardingComplete()) return@withContext null

        UserProfile(
            name = securePrefs.getString(KEY_NAME) ?: "",
            preferredName = securePrefs.getString(KEY_PREFERRED_NAME) ?: "",
            age = securePrefs.getInt(KEY_AGE),
            interests = securePrefs.getStringSet(KEY_INTERESTS)?.toList() ?: emptyList(),
            conversationStyle = securePrefs.getString(KEY_CONVERSATION_STYLE)
                ?.let { ConversationStyle.valueOf(it) } ?: ConversationStyle.BALANCED,
            privacyConsent = securePrefs.getBoolean(KEY_PRIVACY_CONSENT) ?: false,
            voiceEnabled = securePrefs.getBoolean(KEY_VOICE_ENABLED) ?: true,
            createdAt = securePrefs.getLong(KEY_CREATED_AT) ?: System.currentTimeMillis()
        )
    }

    suspend fun isOnboardingComplete(): Boolean = withContext(Dispatchers.IO) {
        securePrefs.getBoolean(KEY_ONBOARDING_COMPLETE) ?: false
    }

    suspend fun deleteUserData() = withContext(Dispatchers.IO) {
        securePrefs.clear()
    }

    companion object {
        private const val KEY_NAME = "user_name"
        private const val KEY_PREFERRED_NAME = "user_preferred_name"
        private const val KEY_AGE = "user_age"
        private const val KEY_INTERESTS = "user_interests"
        private const val KEY_CONVERSATION_STYLE = "conversation_style"
        private const val KEY_PRIVACY_CONSENT = "privacy_consent"
        private const val KEY_VOICE_ENABLED = "voice_enabled"
        private const val KEY_CREATED_AT = "created_at"
        private const val KEY_ONBOARDING_COMPLETE = "onboarding_complete"
    }
}

