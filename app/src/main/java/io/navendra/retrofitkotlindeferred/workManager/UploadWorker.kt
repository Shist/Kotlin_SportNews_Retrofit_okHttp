package io.navendra.retrofitkotlindeferred.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        NewsRepository.getInstance(applicationContext).loadNews()

        return Result.success()
    }

}