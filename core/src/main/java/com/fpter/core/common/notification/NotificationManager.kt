package com.fpter.core.common.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.fpter.core.R

class NotificationManager(private val context: Context) {

    companion object {
        const val NOTIFICATION_ID = 200
    }

    fun showNotification(errorMessage: String) {
        val notification = NotificationCompat.Builder(context, "Notification")
            .setSmallIcon(R.drawable.ic_launcher_background)
            //.setColor(ContextCompat.getColor(context, R.color.purple_150))
            //.setContentTitle(title)
            .setContentText(errorMessage)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        //NotificationManagerCompat.from(context).notify(200, notification)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_HIGH)
            with(context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager) {
                createNotificationChannel(channel)
                notify(NOTIFICATION_ID, notification)
            }
        } else {
            with(NotificationManagerCompat.from(context)) {
                notify(NOTIFICATION_ID, notification)
            }
        }
    }
}