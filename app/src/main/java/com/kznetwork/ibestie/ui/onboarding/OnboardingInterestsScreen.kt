package com.kznetwork.ibestie.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kznetwork.ibestie.viewmodel.OnboardingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingInterestsScreen(
    viewModel: OnboardingViewModel,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val predefinedInterests = listOf(
        "Music", "Sports", "Reading", "Gaming", "Movies",
        "Cooking", "Travel", "Art", "Technology", "Fitness",
        "Photography", "Nature", "Fashion", "Writing"
    )
    
    var selectedInterests by remember { mutableStateOf(setOf<String>()) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
        
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "What are you interested in?",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Pick a few topics you'd like to chat about",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Interests chips in a lazy grid-like layout
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                predefinedInterests.chunked(3).forEach { rowInterests ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowInterests.forEach { interest ->
                            FilterChip(
                                selected = selectedInterests.contains(interest),
                                onClick = {
                                    selectedInterests = if (selectedInterests.contains(interest)) {
                                        selectedInterests - interest
                                    } else {
                                        selectedInterests + interest
                                    }
                                    viewModel.updateInterests(selectedInterests.toList())
                                },
                                label = { Text(interest) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        // Fill remaining space in row
                        repeat(3 - rowInterests.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        
        Column {
            Button(
                onClick = onNext,
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedInterests.isNotEmpty()
            ) {
                Text("Next")
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            TextButton(
                onClick = onNext,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Skip")
            }
        }
    }
}

