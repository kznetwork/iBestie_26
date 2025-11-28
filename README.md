# iBestie - Voice Emotional Companion App

**Author**: 김대경  
**Reviewer**: 전체 팀원 명

**iBestie Next** is an Android voice-first emotional companion app that provides empathetic, personalized conversations through continuous voice chat powered by AI.

---

## 📋 Project Overview

- **Platform**: Android (Kotlin)
- **Architecture**: MVVM + Jetpack Compose
- **Voice**: Vosk (offline STT) + Google Speech API (fallback) + Android TTS
- **Backend**: FastAPI (secure GPT proxy)
- **Storage**: EncryptedSharedPreferences
- **Analytics**: Mixpanel + Firebase Crashlytics

---

## 🎯 Features

### ✅ Step 1 Completed (Current)
- ✅ Android project structure with MVVM architecture
- ✅ Jetpack Compose UI framework
- ✅ 7-screen onboarding flow
  1. Welcome
  2. Voice introduction
  3. Privacy consent
  4. Name input
  5. Age input (optional)
  6. Interests selection
  7. Conversation style preference
- ✅ Chat screen with voice input placeholders
- ✅ Encrypted local storage for user data
- ✅ Repository pattern for data management
- ✅ Base ViewModels with error handling

### 🔜 Upcoming Steps
- **Step 2**: Enhance onboarding UI (Figma-inspired design)
- **Step 3**: Implement encrypted personalization storage
- **Step 4**: Build SpeechManager (STT/TTS integration)
- **Step 5**: Create FastAPI GPT proxy backend
- **Step 6**: Integrate persona engine + memory model
- **Step 7**: Add analytics events (Mixpanel)
- **Step 8**: Firebase deployment + CI/CD pipeline

---

## 🏗️ Architecture

```
iBestie_26/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/kznetwork/ibestie/
│   │   │   │   ├── ui/                   # Compose UI components
│   │   │   │   │   ├── navigation/       # NavHost & Screen definitions
│   │   │   │   │   ├── onboarding/       # 7 onboarding screens
│   │   │   │   │   ├── chat/             # Chat screen
│   │   │   │   │   └── theme/            # Material3 theme
│   │   │   │   ├── viewmodel/            # ViewModels (MVVM)
│   │   │   │   ├── repository/           # Data repositories
│   │   │   │   ├── model/                # Data models
│   │   │   │   ├── data/local/           # Encrypted storage
│   │   │   │   ├── util/                 # Utilities
│   │   │   │   ├── BestieApplication.kt  # Application class
│   │   │   │   └── MainActivity.kt       # Main activity
│   │   │   ├── res/                      # Android resources
│   │   │   └── AndroidManifest.xml
│   │   └── test/                         # Unit tests
│   ├── build.gradle.kts                  # App-level Gradle
│   └── proguard-rules.pro
├── build.gradle.kts                      # Project-level Gradle
├── settings.gradle.kts
├── gradle.properties
└── README.md
```

---

## 🛠️ Tech Stack

### Frontend (Android)
- **Language**: Kotlin 1.9.20
- **UI**: Jetpack Compose + Material3
- **Architecture**: MVVM (ViewModel + Repository pattern)
- **Async**: Coroutines + Flow
- **Navigation**: Navigation Compose
- **Security**: EncryptedSharedPreferences (AES256-GCM)

### Backend (TODO - Step 5)
- **Framework**: FastAPI
- **AI**: OpenAI GPT-4 (via secure proxy)
- **Deployment**: Firebase Functions or Google Cloud Run

### Analytics & Monitoring
- **Crashlytics**: Firebase Crashlytics
- **Analytics**: Mixpanel
- **Remote Config**: Firebase (for A/B testing)

### Voice Tech (TODO - Step 4)
- **STT**: Vosk (offline) + Google Speech API (fallback)
- **TTS**: Android TextToSpeech API

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or later
- JDK 17
- Android SDK 34
- Gradle 8.2+

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/kznetwork/iBestie_26.git
cd iBestie_26
```

2. **Open in Android Studio**
```bash
open -a "Android Studio" .
```

3. **Add Firebase Configuration** (TODO)
- Download `google-services.json` from Firebase Console
- Place it in `app/` directory

4. **Configure Mixpanel** (TODO)
- Update `MIXPANEL_TOKEN` in `BestieApplication.kt`

5. **Sync Gradle & Build**
```bash
./gradlew build
```

6. **Run on Emulator or Device**
- Click ▶️ Run in Android Studio
- Or use: `./gradlew installDebug`

---

## 📱 Running the App

### Development Build
```bash
./gradlew assembleDebug
```

### Run Tests
```bash
./gradlew test
./gradlew connectedAndroidTest
```

### Generate Release APK
```bash
./gradlew assembleRelease
```

---

## 🔐 Security & Privacy

- **Local-First**: All user data stored locally with AES-256 encryption
- **No Data Sharing**: Personal information never leaves the device
- **API Key Protection**: GPT API key stored in backend proxy (not in client)
- **Delete Anytime**: Users can delete all their data with one tap
- **Compliance**: GDPR-ready architecture

---

## 📝 Development Guidelines

### Code Style
- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable/function names
- Add TODO comments for human decisions needed
- Document public APIs with KDoc

### Branching Strategy
- `main`: Production-ready code
- `staging`: Integration & testing
- `feature/*`: Feature branches (e.g., `feature/step2-ui-enhancement`)

### Commit Messages
```
feat: Add voice input button to chat screen
fix: Resolve encrypted storage crash on API 26
docs: Update README with Step 2 progress
test: Add unit tests for ChatViewModel
```

---

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumentation Tests
```bash
./gradlew connectedAndroidTest
```

### Coverage Report
```bash
./gradlew jacocoTestReport
```

---

## 📊 Analytics Events (TODO - Step 7)

Key events to track:
- `onboarding_started`
- `onboarding_completed`
- `voice_message_sent`
- `text_message_sent`
- `conversation_length`
- `user_emotion_detected`
- `app_crash`

---

## 🚧 TODO Checklist

### Step 1 - Base Structure ✅
- [x] Android folder structure
- [x] MVVM architecture setup
- [x] Gradle dependencies
- [x] Navigation graph
- [x] 7 onboarding screens (placeholders)
- [x] Chat screen (placeholder)
- [x] Encrypted storage wrapper

### Step 2 - UI Enhancement (Next)
- [ ] Figma-inspired design system
- [ ] Custom animations for onboarding
- [ ] Improved chat UI with message bubbles
- [ ] Voice waveform animation
- [ ] Dark mode support

### Step 3 - Data Storage
- [ ] Complete SecurePreferences initialization
- [ ] Implement "Delete My Data" flow
- [ ] Add data export feature (GDPR)

### Step 4 - Voice Engine
- [ ] Integrate Vosk for offline STT
- [ ] Add Google Speech API fallback
- [ ] Implement continuous listening mode
- [ ] Add TTS for Bestie responses
- [ ] Permission handling for microphone

### Step 5 - Backend
- [ ] Create FastAPI server
- [ ] Implement GPT-4 proxy endpoint
- [ ] Add request authentication
- [ ] Deploy to Cloud Run/Firebase

### Step 6 - Persona AI
- [ ] Design persona prompt engineering
- [ ] Implement conversation memory
- [ ] Add emotion detection
- [ ] Context-aware responses

### Step 7 - Analytics
- [ ] Setup Mixpanel events
- [ ] Configure Crashlytics
- [ ] Add user properties tracking
- [ ] Create analytics dashboard

### Step 8 - Deployment
- [ ] Setup GitHub Actions CI/CD
- [ ] Configure Firebase App Distribution
- [ ] Add ProGuard rules for release
- [ ] Generate signed APK
- [ ] Play Store listing preparation

---

## 🤝 Contributing

This is a private project. For internal team members:

1. Create a feature branch from `staging`
2. Make your changes with clear commits
3. Submit a PR for review
4. After approval, merge to `staging`
5. Release manager will merge `staging` → `main`

---

## 📄 License

Copyright © 2025 KZ Network. All rights reserved.

---

## 👥 Team

- **Lead Engineer**: 김대경
- **Reviewer**: 전체 팀원 명
- **AI Assistant**: Cursor AI

---

## 📞 Support

For questions or issues, contact: dev@kznetwork.com

---

**Built with ❤️ using Cursor AI**
