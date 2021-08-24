package com.antonchuraev.becomebetter.base

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import com.antonchuraev.becomebetter.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e("tag", "onMessageReceived From:${remoteMessage}")
    }

    override fun onNewToken(token: String) {
        Log.e("tag", "Refreshed token: $token")

    }

    override fun onMessageSent(message: String) {
        super.onMessageSent(message)
        Log.e("tag", "onMessageSent:$message")
    }
}