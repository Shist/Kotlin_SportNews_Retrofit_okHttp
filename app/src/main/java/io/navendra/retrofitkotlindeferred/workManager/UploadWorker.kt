package io.navendra.retrofitkotlindeferred.workManager

import android.content.Context
import androidx.work.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.repository.NewsRepository

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