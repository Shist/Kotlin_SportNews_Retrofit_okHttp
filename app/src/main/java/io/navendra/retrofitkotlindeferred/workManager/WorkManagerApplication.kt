package io.navendra.retrofitkotlindeferred.workManager

import android.app.Application
import android.util.Log
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit


class WorkManagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val currentDate = Calendar.getInstance()

        val dueDate = Calendar.getInstance()
        dueDate[Calendar.HOUR_OF_DAY] = 8
        dueDate[Calendar.MINUTE] = 0
        dueDate[Calendar.SECOND] = 0

        if(dueDate.before(currentDate)) {
            dueDate.add(Calendar.DAY_OF_MONTH, 1);
        }

        val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis

        val saveRequest =
            PeriodicWorkRequestBuilder<UploadWorker>(24, TimeUnit.HOURS)
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .build()

        Log.d("MyLog", "WorkManager: dueDate:\n$dueDate\ncurrentDate:\n$currentDate")
        Log.d("MyLog", "WorkManager: timeDiff:\n$timeDiff")

        WorkManager.getInstance(applicationContext).enqueue(saveRequest)
    }

}