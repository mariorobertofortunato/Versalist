package com.evenclose.versalistpro

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.PendingIntent.getActivity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.resources.Compatibility.Api18Impl.setAutoCancel
import androidx.compose.material.icons.Icons
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.evenclose.versalistpro.data.repository.ListRepository
import com.evenclose.versalistpro.domain.use_case.UseCase
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver: BroadcastReceiver() {

    @Inject
    lateinit var listRepository: ListRepository

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context?, intent: Intent?) {

        val listName = intent?.getStringExtra("list_name")
        val listId = intent?.getIntExtra("list_id",0)

        val notificationManager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel = NotificationChannel(
            "channel_id",
            "notification_channel",
            NotificationManager.IMPORTANCE_HIGH).apply {
            description = "notification_description"
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
        }
        notificationManager.createNotificationChannel(notificationChannel)

        // TODO GEstire DeepLink che porta direttamente alla lista
        val tapResultIntent = Intent(context, MainActivity::class.java)
        tapResultIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent: PendingIntent = getActivity(context, 0, tapResultIntent, FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)

        val notification = context.let {
            NotificationCompat.Builder(it, "channel_id")
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText("Don't forget about this list: $listName")
                .setSmallIcon(R.drawable.ic_notification)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .build()
        }

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        notificationManager.notify(listId ?: 0, notification)

        // When the notif is fired we update the reminderDate field of the list
        GlobalScope.launch {
            listRepository.updateMainListReminder(listId ?: 0, null)
            listRepository.fetchAllLists()
        }

    }
}