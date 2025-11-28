package com.kznetwork.ibestie

import android.app.Application
// import com.google.firebase.FirebaseApp  // TODO: Re-enable when Firebase is configured
import com.mixpanel.android.mpmetrics.MixpanelAPI

class BestieApplication : Application() {

    companion object {
        lateinit var mixpanel: MixpanelAPI
            private set
        
        // TODO: Add Mixpanel project token from Firebase Remote Config or BuildConfig
        private const val MIXPANEL_TOKEN = "YOUR_MIXPANEL_TOKEN"
    }

    override fun onCreate() {
        super.onCreate()
        
        // Initialize Firebase (Temporarily disabled for testing)
        // TODO: Re-enable when google-services.json is added
        // FirebaseApp.initializeApp(this)
        
        // Initialize Mixpanel
        mixpanel = MixpanelAPI.getInstance(this, MIXPANEL_TOKEN, true)
    }
}

