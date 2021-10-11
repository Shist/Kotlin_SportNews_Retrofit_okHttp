package io.navendra.retrofitkotlindeferred.ui.fragments

import android.view.View
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SnackbarAction: View.OnClickListener, KoinComponent {

    private val viewModel: NewsListViewModel by inject()

    override fun onClick(v: View?) {
        viewModel.loadData()
    }

}