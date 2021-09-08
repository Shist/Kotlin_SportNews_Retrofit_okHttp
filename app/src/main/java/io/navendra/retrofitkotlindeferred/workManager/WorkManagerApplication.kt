package io.navendra.retrofitkotlindeferred.workManager

import android.app.Application
import android.util.Log
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager

class WorkManagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val saveRequest =
            PeriodicWorkRequestBuilder<UploadWorker>(15,
                java.util.concurrent.TimeUnit.MINUTES)
                .build()

        Log.d("MyLog", "WorkManager: Successfully loaded data...")

        WorkManager.getInstance(applicationContext).enqueue(saveRequest)
    }

}