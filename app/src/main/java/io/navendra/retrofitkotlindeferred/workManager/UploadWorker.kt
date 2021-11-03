package io.navendra.retrofitkotlindeferred.workManager

import android.content.Context
import androidx.work.*
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepositoryImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams), KoinComponent {

    private val newsRepositoryImpl: NewsRepositoryImpl by inject()

    override suspend fun doWork(): Result {

        try {
            newsRepositoryImpl.loadNews()
        } catch (e: Throwable) {
            return Result.failure()
        }

        return Result.success()
    }

}