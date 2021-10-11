package io.navendra.retrofitkotlindeferred.workManager

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class WorkManagerApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

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

        WorkManager.getInstance(applicationContext).enqueue(saveRequest)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

}