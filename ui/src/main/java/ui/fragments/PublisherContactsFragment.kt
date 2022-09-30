package ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ui.databinding.NewsPublisherContactsBinding

class PublisherContactsFragment : Fragment() {

    private var _binding: NewsPublisherContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsPublisherContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView = binding.webView
        webView.loadUrl("https://www.beinsports.com/us/contact-us/")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}