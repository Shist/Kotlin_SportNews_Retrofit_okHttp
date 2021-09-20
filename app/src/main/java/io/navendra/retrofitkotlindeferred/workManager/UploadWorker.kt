package io.navendra.retrofitkotlindeferred.workManager

import android.content.Context
import android.util.Log
import androidx.work.*
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        NewsRepository.getInstance(applicationContext).loadNews()

        return Result.success()
    }

}