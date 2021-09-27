package io.navendra.retrofitkotlindeferred.koinModules

import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import org.koin.dsl.module

val newsRepositoryModule = module {
    single { NewsRepository(get()) }
}
