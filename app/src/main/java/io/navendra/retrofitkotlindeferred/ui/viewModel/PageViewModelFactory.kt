package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModelFactory(private val app: Application, private val item_id: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsPageViewModel::class.java)) {
            return NewsPageViewModel(app, item_id) as T
        }
        throw IllegalArgumentException("Error in PageViewModelFactory.kt : Unknown ViewModel class")
    }
}