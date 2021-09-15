package io.navendra.retrofitkotlindeferred.workManager

import android.content.Context
import androidx.work.*
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import java.util.*
import java.util.concurrent.TimeUnit

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        val saveRequest =
            PeriodicWorkRequestBuilder<UploadWorker>(24,
                java.util.concurrent.TimeUnit.HOURS)
                .build()

        NewsRepository.getInstance(applicationContext).loadNews()

        return Result.success()
    }

}