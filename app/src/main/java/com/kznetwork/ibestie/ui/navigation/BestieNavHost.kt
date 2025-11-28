package com.kznetwork.ibestie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kznetwork.ibestie.ui.onboarding.*
import com.kznetwork.ibestie.ui.chat.ChatScreen
import com.kznetwork.ibestie.viewmodel.OnboardingViewModel
import com.kznetwork.ibestie.viewmodel.ChatViewModel

/**
 * Main navigation host for the app
 */
@Composable
fun BestieNavHost() {
    val navController = rememberNavController()
    
    // TODO: Check if onboarding is complete and start at appropriate screen
    val startDestination = Screen.OnboardingWelcome.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Onboarding Flow
        composable(Screen.OnboardingWelcome.route) {
            val viewModel: OnboardingViewModel = viewModel()
            OnboardingWelcomeScreen(
                onNext = { navController.navigate(Screen.OnboardingVoice.route) }
            )
        }
        
        composable(Screen.OnboardingVoice.route) {
            OnboardingVoiceScreen(
                onNext = { navController.navigate(Screen.OnboardingPrivacy.route) },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.OnboardingPrivacy.route) {
            val viewModel: OnboardingViewModel = viewModel()
            OnboardingPrivacyScreen(
                onNext = { 
                    viewModel.setPrivacyConsent(true)
                    navController.navigate(Screen.OnboardingName.route) 
                },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.OnboardingName.route) {
            val viewModel: OnboardingViewModel = viewModel()
            OnboardingNameScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(Screen.OnboardingAge.route) },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.OnboardingAge.route) {
            val viewModel: OnboardingViewModel = viewModel()
            OnboardingAgeScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(Screen.OnboardingInterests.route) },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.OnboardingInterests.route) {
            val viewModel: OnboardingViewModel = viewModel()
            OnboardingInterestsScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(Screen.OnboardingStyle.route) },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.OnboardingStyle.route) {
            val viewModel: OnboardingViewModel = viewModel()
            OnboardingStyleScreen(
                viewModel = viewModel,
                onComplete = { 
                    viewModel.completeOnboarding {
                        navController.navigate(Screen.Chat.route) {
                            popUpTo(Screen.OnboardingWelcome.route) { inclusive = true }
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
        
        // Main Chat Screen
        composable(Screen.Chat.route) {
            val viewModel: ChatViewModel = viewModel()
            ChatScreen(viewModel = viewModel)
        }
    }
}

sealed class Screen(val route: String) {
    object OnboardingWelcome : Screen("onboarding_welcome")
    object OnboardingVoice : Screen("onboarding_voice")
    object OnboardingPrivacy : Screen("onboarding_privacy")
    object OnboardingName : Screen("onboarding_name")
    object OnboardingAge : Screen("onboarding_age")
    object OnboardingInterests : Screen("onboarding_interests")
    object OnboardingStyle : Screen("onboarding_style")
    object Chat : Screen("chat")
}

