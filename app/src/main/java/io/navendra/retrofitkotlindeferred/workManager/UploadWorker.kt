package io.navendra.retrofitkotlindeferred.workManager

import android.content.Context
import androidx.work.*
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams), KoinComponent {

    private val newsRepository: NewsRepository by inject()

    override suspend fun doWork(): Result {

        newsRepository.loadNews()

        return Result.success()
    }

}