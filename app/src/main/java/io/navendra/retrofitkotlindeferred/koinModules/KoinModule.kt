package io.navendra.retrofitkotlindeferred.koinModules

import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import org.koin.dsl.module

object KoinModule {

    val newsRepositoryModule = module {
        single { NewsRepository(get()) }
    }

}
