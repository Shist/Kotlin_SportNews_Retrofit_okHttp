package io.navendra.retrofitkotlindeferred.ui.fragments

import android.view.View
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsListViewModel

class SnackbarAction(_viewModel: NewsListViewModel): View.OnClickListener {

    private var viewModel: NewsListViewModel = _viewModel

    override fun onClick(v: View?) {
        viewModel.loadData()
    }

}