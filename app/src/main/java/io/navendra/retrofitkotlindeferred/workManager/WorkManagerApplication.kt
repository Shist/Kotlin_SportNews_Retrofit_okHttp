package io.navendra.retrofitkotlindeferred.workManager

import android.app.Application
import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager

class WorkManagerApplication : Application() {

    companion object {
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext

        val saveRequest =
            PeriodicWorkRequestBuilder<UploadWorker>(24,
                java.util.concurrent.TimeUnit.HOURS)
                .build()

        WorkManager.getInstance(ctx!!).enqueue(saveRequest)
    }

}