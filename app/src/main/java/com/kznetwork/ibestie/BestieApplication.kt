package com.kznetwork.ibestie

import android.app.Application
import com.google.firebase.FirebaseApp
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
        
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        
        // Initialize Mixpanel
        mixpanel = MixpanelAPI.getInstance(this, MIXPANEL_TOKEN, true)
    }
}

