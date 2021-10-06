package io.navendra.retrofitkotlindeferred.daggerModules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class DaggerHiltModule {
    @Provides
    @Singleton
    fun provideContext(context: Context): Context = context
}