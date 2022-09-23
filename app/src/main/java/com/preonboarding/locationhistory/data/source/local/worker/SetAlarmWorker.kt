package com.preonboarding.locationhistory.data.source.local.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.preonboarding.locationhistory.data.repository.TimerRepository
import com.preonboarding.locationhistory.util.Alarm
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

/**
 * @Created by 김현국 2022/09/23
 */
@HiltWorker
class SetAlarmWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val timerRepository: TimerRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return coroutineScope {
            timerRepository.getDuration().collect { time ->
                Alarm.create(appContext, time)
            }
            Result.success()
        }
    }
}
