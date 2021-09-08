package io.navendra.retrofitkotlindeferred.ui.fragments

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsListViewModel

class SnackbarAction(_viewModel: NewsListViewModel): View.OnClickListener {

    private var viewModel: NewsListViewModel = _viewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        viewModel.loadData()
    }

}