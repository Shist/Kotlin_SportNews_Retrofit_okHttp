package io.navendra.retrofitkotlindeferred.workManager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository

@HiltWorker
class UploadWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val newsRepository: NewsRepository
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        newsRepository.loadNews()

        return Result.success()
    }

}