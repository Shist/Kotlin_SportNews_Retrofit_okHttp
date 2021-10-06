package io.navendra.retrofitkotlindeferred.workManager

import android.content.Context
import androidx.work.*
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import javax.inject.Inject

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {

    @Inject
    lateinit var newsRepository: NewsRepository

    override suspend fun doWork(): Result {

        newsRepository.loadNews()

        return Result.success()
    }

}