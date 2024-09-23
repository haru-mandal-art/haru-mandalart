package com.coldblue.data.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.coldblue.data.mapper.AlarmMapper.asDomain
import com.coldblue.database.dao.NotificationDao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BootCompletedReceiver : BroadcastReceiver() {
    @Inject
    lateinit var notificationScheduler: NotificationScheduler

    @Inject
    lateinit var notificationDao: NotificationDao

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            addAllNotification()
        }
    }

    private fun addAllNotification(){
        CoroutineScope(Dispatchers.IO).launch {
            notificationDao.getAllNotification().forEach {
                notificationScheduler.add(it.asDomain())
            }
        }
    }
}
