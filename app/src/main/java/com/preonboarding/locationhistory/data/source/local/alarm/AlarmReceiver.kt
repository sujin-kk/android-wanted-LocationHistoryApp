package com.preonboarding.locationhistory.data.source.local.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.preonboarding.locationhistory.R
import com.preonboarding.locationhistory.data.source.local.worker.LocationWorker
import com.preonboarding.locationhistory.data.source.local.worker.SetAlarmWorker
import kotlinx.coroutines.*

/**
 * @Created by 김현국 2022/09/21
 */
class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            when (intent?.action) {
                context.getString(R.string.setting_intent) -> {
                    WorkManager.getInstance(context).enqueue(
                        OneTimeWorkRequestBuilder<LocationWorker>().build()
                    )
                }
                context.getString(R.string.setting_boot_intent) -> {
                    WorkManager.getInstance(context).enqueue(
                        OneTimeWorkRequestBuilder<SetAlarmWorker>().build()
                    )
                }
            }
        }
    }
}
