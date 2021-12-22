package com.work_manager

import android.content.Context
import androidx.work.*
import data.workManager.UploadWorker
import java.util.*
import java.util.concurrent.TimeUnit

class NewsWorkManager {

    fun startWorkManager(appContext: Context) {

        val currentDate = Calendar.getInstance()

        val dueDate = Calendar.getInstance()
        dueDate[Calendar.HOUR_OF_DAY] = 8
        dueDate[Calendar.MINUTE] = 0
        dueDate[Calendar.SECOND] = 0

        if(dueDate.before(currentDate)) {
            dueDate.add(Calendar.DAY_OF_MONTH, 1)
        }

        val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis

        val saveRequest =
            PeriodicWorkRequestBuilder<UploadWorker>( 24, TimeUnit.HOURS)
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .build()

        WorkManager.getInstance(appContext).enqueue(saveRequest)
    }

}