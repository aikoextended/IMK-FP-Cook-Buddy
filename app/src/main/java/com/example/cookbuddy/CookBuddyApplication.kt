package com.example.cookbuddy

import android.app.Application
import com.google.firebase.FirebaseApp

class CookBuddyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}