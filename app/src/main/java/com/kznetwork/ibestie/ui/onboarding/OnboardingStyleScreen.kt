package com.kznetwork.ibestie.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kznetwork.ibestie.model.ConversationStyle
import com.kznetwork.ibestie.viewmodel.OnboardingViewModel

@Composable
fun OnboardingStyleScreen(
    viewModel: OnboardingViewModel,
    onComplete: () -> Unit,
    onBack: () -> Unit
) {
    var selectedStyle by remember { mutableStateOf(ConversationStyle.BALANCED) }
    
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
                text = "How should I talk to you?",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            ConversationStyleOption(
                title = "Casual",
                description = "Relaxed and informal, like chatting with a friend",
                selected = selectedStyle == ConversationStyle.CASUAL,
                onClick = {
                    selectedStyle = ConversationStyle.CASUAL
                    viewModel.updateConversationStyle(ConversationStyle.CASUAL)
                }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            ConversationStyleOption(
                title = "Balanced",
                description = "A mix of friendly and supportive",
                selected = selectedStyle == ConversationStyle.BALANCED,
                onClick = {
                    selectedStyle = ConversationStyle.BALANCED
                    viewModel.updateConversationStyle(ConversationStyle.BALANCED)
                }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            ConversationStyleOption(
                title = "Supportive",
                description = "Empathetic and understanding, focused on listening",
                selected = selectedStyle == ConversationStyle.SUPPORTIVE,
                onClick = {
                    selectedStyle = ConversationStyle.SUPPORTIVE
                    viewModel.updateConversationStyle(ConversationStyle.SUPPORTIVE)
                }
            )
        }
        
        Button(
            onClick = onComplete,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Chatting")
        }
    }
}

@Composable
fun ConversationStyleOption(
    title: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) 
                MaterialTheme.colorScheme.primaryContainer 
            else 
                MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = if (selected) 
                    MaterialTheme.colorScheme.onPrimaryContainer 
                else 
                    MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = if (selected) 
                    MaterialTheme.colorScheme.onPrimaryContainer 
                else 
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

