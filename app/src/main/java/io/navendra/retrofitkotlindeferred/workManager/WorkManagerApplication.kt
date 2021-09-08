package io.navendra.retrofitkotlindeferred.workManager

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager

class WorkManagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val saveRequest =
            PeriodicWorkRequestBuilder<UploadWorker>(24,
                java.util.concurrent.TimeUnit.HOURS)
                .build()

        WorkManager.getInstance(applicationContext).enqueue(saveRequest)
    }

}