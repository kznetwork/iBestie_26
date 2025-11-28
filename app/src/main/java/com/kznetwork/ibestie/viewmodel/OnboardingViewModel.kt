package com.kznetwork.ibestie.viewmodel

import androidx.lifecycle.viewModelScope
import com.kznetwork.ibestie.model.ConversationStyle
import com.kznetwork.ibestie.model.UserProfile
import com.kznetwork.ibestie.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for onboarding flow
 */
class OnboardingViewModel(
    private val userRepository: UserRepository = UserRepository()
) : BaseViewModel() {

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep.asStateFlow()

    fun updateName(name: String) {
        _userProfile.value = _userProfile.value.copy(name = name)
    }

    fun updatePreferredName(preferredName: String) {
        _userProfile.value = _userProfile.value.copy(preferredName = preferredName)
    }

    fun updateAge(age: Int?) {
        _userProfile.value = _userProfile.value.copy(age = age)
    }

    fun updateInterests(interests: List<String>) {
        _userProfile.value = _userProfile.value.copy(interests = interests)
    }

    fun updateConversationStyle(style: ConversationStyle) {
        _userProfile.value = _userProfile.value.copy(conversationStyle = style)
    }

    fun setPrivacyConsent(consent: Boolean) {
        _userProfile.value = _userProfile.value.copy(privacyConsent = consent)
    }

    fun nextStep() {
        _currentStep.value = _currentStep.value + 1
    }

    fun previousStep() {
        if (_currentStep.value > 0) {
            _currentStep.value = _currentStep.value - 1
        }
    }

    fun completeOnboarding(onComplete: () -> Unit) {
        launchSafe {
            userRepository.saveUserProfile(_userProfile.value)
            viewModelScope.launch {
                onComplete()
            }
        }
    }
}

