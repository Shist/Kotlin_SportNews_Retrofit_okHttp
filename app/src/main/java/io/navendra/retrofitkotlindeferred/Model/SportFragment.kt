package io.navendra.retrofitkotlindeferred.Model

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import io.navendra.retrofitkotlindeferred.R

class SportFragment : Fragment() {

    companion object {
        fun newInstance() = SportFragment()
    }

    private lateinit var viewModel: SportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sport_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.news.observe(this.viewLifecycleOwner, Observer {})
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SportViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

// Два фрагмента: для блока новостей и для деталей одной какой-то новости (при нажатии на неё)