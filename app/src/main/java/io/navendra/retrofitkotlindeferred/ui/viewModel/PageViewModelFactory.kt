package io.navendra.retrofitkotlindeferred.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModelFactory(private val item_id: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Int::class.java).newInstance(item_id)
    }
}