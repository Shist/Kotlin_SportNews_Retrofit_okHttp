package com.work_manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import domain.NewsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams), KoinComponent {

    private val newsRepository: NewsRepository by inject()

    override suspend fun doWork(): Result {

        try {
            newsRepository.loadNews()
        } catch (e: Throwable) {
            return Result.failure()
        }

        return Result.success()
    }

}