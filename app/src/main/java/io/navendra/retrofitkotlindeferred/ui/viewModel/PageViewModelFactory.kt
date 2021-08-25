package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModelFactory(private val app: Application, private val itemId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsPageViewModel::class.java)) {
            return NewsPageViewModel(app, itemId) as T
        }
        throw IllegalArgumentException("Error in PageViewModelFactory.kt : Unknown ViewModel class")
    }
}