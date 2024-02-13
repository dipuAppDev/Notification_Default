package com.example.notification_default

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import com.example.notification_default.Utils.CHANNEL_ID
import com.example.notification_default.Utils.CHANNEL_NAME
import com.example.notification_default.Utils.NOTIFICATION_CHANNEL_DESCRIPTION
import com.example.notification_default.Utils.NOTIFICATION_ID
import com.example.notification_default.Utils.SET_CONTENT_TEXT
import com.example.notification_default.Utils.SET_CONTENT_TITLE

class MainActivity : AppCompatActivity() {
    private lateinit var notificationBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationBtn = findViewById(R.id.showNotificationBtn)
        createNotificationChannel()
        notificationBtn.setOnClickListener {
            showNotification()
        }
    }
    private fun showNotification() {
        val notificationBuilder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle(SET_CONTENT_TITLE)
            .setContentText(SET_CONTENT_TEXT)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID,notificationBuilder.build())
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT)
                .apply {
                    description = NOTIFICATION_CHANNEL_DESCRIPTION
                }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}