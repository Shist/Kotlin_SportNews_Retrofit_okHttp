package com.app

import android.app.Application
import androidx.work.*
import data.koinModule.dataModule
import data.workManager.UploadWorker
import ui.koinModule.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*
import java.util.concurrent.TimeUnit

class WorkManagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WorkManagerApplication)
            modules(dataModule, uiModule)
        }

        val currentDate = Calendar.getInstance()

        val dueDate = Calendar.getInstance()
        dueDate[Calendar.HOUR_OF_DAY] = 8
        dueDate[Calendar.MINUTE] = 0
        dueDate[Calendar.SECOND] = 0

        if(dueDate.before(currentDate)) {
            dueDate.add(Calendar.DAY_OF_MONTH, 1)
        }

        val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis

        val saveRequest =
            PeriodicWorkRequestBuilder<UploadWorker>(24, TimeUnit.HOURS)
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .build()

        WorkManager.getInstance(applicationContext).enqueue(saveRequest)
    }

}