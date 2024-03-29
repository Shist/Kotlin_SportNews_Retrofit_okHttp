package com.shist

import android.app.Application
import com.view_model.koinModule.viewModelModule
import com.work_manager.NewsWorkManager
import data.koinModule.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(dataModule, viewModelModule)
        }

        NewsWorkManager().startWorkManager(applicationContext)
    }

}